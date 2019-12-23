package com.jicl.controller.admin;

import com.jicl.constant.BlogDataDictionary;
import com.jicl.dto.LoginDto;
import com.jicl.entity.User;
import com.jicl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 管理员登录管理
 *
 * @author : xianzilei
 * @date : 2019/12/21 10:37
 */
@Controller
@RequestMapping("/admin")
public class AdminLoginController {

    @Autowired
    private UserService userService;

    /**
     * 功能描述: 跳转管理员登录界面
     *
     * @return java.lang.String
     * @author xianzilei
     * @date 2019/12/21 11:12
     **/
    @RequestMapping("/toAdminLoginPage")
    public String toAdminLoginPage() {
        return "admin/login";
    }

    /**
     * 功能描述: 管理员登录
     *
     * @param loginDto   1
     * @param request    2
     * @param attributes 3
     * @return java.lang.String
     * @author xianzilei
     * @date 2019/12/21 11:11
     **/
    @RequestMapping("/login")
    public String login(LoginDto loginDto, HttpSession session, HttpServletRequest request,
                        RedirectAttributes attributes) {
        //用户名和密码校验
        loginDto.setUserRole(BlogDataDictionary.USER_ROLE_SUPER_ADMIN);
        User user = userService.checkUser(loginDto);
        if (user != null) {
            userService.updateLoginInfo(user, request.getRemoteAddr());
            user.setPassword(null);
            session.setAttribute("user", user);
            return "redirect:/admin/index";
        } else {
            attributes.addFlashAttribute("username", loginDto.getUsername());
            attributes.addFlashAttribute("message", "用户名或密码错误");
            return "redirect:/admin/toAdminLoginPage";
        }
    }

    /**
     * 功能描述: 注销功能
     *
     * @param session 1
     * @return java.lang.String
     * @author xianzilei
     * @date 2019/12/21 11:11
     **/
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("user");
        return "redirect:/toAdminLoginPage";
    }
}
