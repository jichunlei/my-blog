package com.jicl.controller.admin;

import com.jicl.constant.BlogDataDictionary;
import com.jicl.entity.UserExample;
import com.jicl.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.Map;

/**
 * 用户管理：管理员
 *
 * @author : xianzilei
 * @date : 2019/12/21 11:12
 */
@Controller
@RequestMapping("/admin")
public class AdminUserController {

    /**
     * 用户角色对应map
     */
    private static Map<String, String> userRoleMap = new HashMap<>();
    /**
     * 用户状态对应map
     */
    private static Map<String, String> userStatusMap = new HashMap<>();

    static {
        userRoleMap.put(BlogDataDictionary.USER_ROLE_SUPER_ADMIN, "超级管理员");
        userRoleMap.put(BlogDataDictionary.USER_ROLE_GENERAL_ADMIN, "普通管理员");
        userRoleMap.put(BlogDataDictionary.USER_ROLE_VIP_USER, "VIP用户");
        userRoleMap.put(BlogDataDictionary.USER_ROLE_GENERAL_USER, "普通用户");
        userStatusMap.put(BlogDataDictionary.USER_STATUS_NORMAL, "正常");
        userStatusMap.put(BlogDataDictionary.USER_STATUS_BAN, "封禁");
    }

    @Autowired
    private UserService userService;

    /**
     * 功能描述: 个人中心
     *
     * @return java.lang.String
     * @author xianzilei
     * @date 2019/12/18 9:34
     **/
    @RequestMapping("/index")
    public String index() {
        return "admin/index";
    }

    /**
     * 功能描述: 用户管理
     *
     * @return java.lang.String
     * @author xianzilei
     * @date 2019/12/18 9:34
     **/
    @RequestMapping("/users")
    public String users(@RequestParam(defaultValue = "1") Integer pageNum,
                        @RequestParam(defaultValue = "10") Integer pageSize, Model model) {
        UserExample userExample = new UserExample();
        userExample.setOrderByClause("create_time");
        userExample.createCriteria().andDelFlagEqualTo(false);
        model.addAttribute("page", userService.getAllUser(userExample, pageNum, pageSize));
        model.addAttribute("userStatusMap", userStatusMap);
        model.addAttribute("userRoleMap", userRoleMap);
        return "admin/users";
    }

    /**
     * 功能描述: 用户管理
     *
     * @return java.lang.String
     * @author xianzilei
     * @date 2019/12/18 9:34
     **/
    @PostMapping("/users/search")
    public String search(@RequestParam(defaultValue = "1") Integer pageNum,
                         @RequestParam(defaultValue = "10") Integer pageSize, String userRole,
                         String userStatus, String username, Model model) {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        if (StringUtils.isNotBlank(username)) {
            criteria.andUsernameLike("%" + username + "%");
        }
        if (StringUtils.isNotBlank(userRole)) {
            criteria.andUserRoleEqualTo(userRole);
        }
        if (StringUtils.isNotBlank(userStatus)) {
            criteria.andUserStatusEqualTo(userStatus);
        }
        userExample.setOrderByClause("create_time");
        model.addAttribute("page", userService.getAllUser(userExample, pageNum, pageSize));
        model.addAttribute("userRoleMap", userRoleMap);
        model.addAttribute("userStatusMap", userStatusMap);
        return "admin/users:: userList";
    }

    /**
     * 功能描述: 封禁用户
     *
     * @param id 1
     * @return void
     * @author xianzilei
     * @date 2019/12/23 15:46
     **/
    @RequestMapping("/users/{id}/ban")
    public String banUser(@PathVariable Integer id, RedirectAttributes attributes) {
        userService.banUser(id);
        attributes.addFlashAttribute("message", "封禁成功");
        return "redirect:/admin/users";
    }

    /**
     * 功能描述: 解封用户
     *
     * @param id 1
     * @return void
     * @author xianzilei
     * @date 2019/12/23 15:47
     **/
    @RequestMapping("/users/{id}/unBlock")
    public String unBlockUser(@PathVariable Integer id, RedirectAttributes attributes) {
        userService.unblockUser(id);
        attributes.addFlashAttribute("message", "解封成功");
        return "redirect:/admin/users";
    }

    /**
     * 功能描述: 删除用户
     *
     * @param id 1
     * @return void
     * @author xianzilei
     * @date 2019/12/23 15:47
     **/
    @RequestMapping("/users/{id}/delete")
    public String deleteUser(@PathVariable Integer id, RedirectAttributes attributes) {
        userService.deleteUser(id);
        attributes.addFlashAttribute("message", "删除成功");
        return "redirect:/admin/users";
    }
}
