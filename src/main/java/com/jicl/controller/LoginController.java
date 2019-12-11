package com.jicl.controller;

import com.jicl.constant.BlogConstant;
import com.jicl.dto.LoginDto;
import com.jicl.entity.User;
import com.jicl.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 登录控制器
 *
 * @author : xianzilei
 * @date : 2019/11/30 12:11
 */
@Controller
@RequestMapping("/user")
public class LoginController {
    @Autowired
    private UserService userService;

    /**
     * 功能描述: 跳转登录界面
     *
     * @return java.lang.String
     * @author xianzilei
     * @date 2019/12/2 8:43
     **/
    @RequestMapping("/toLoginPage")
    public String toLoginPage(@RequestParam(required = false, defaultValue = "/") String path,
                              Model model) {
        model.addAttribute("path", path);
        return BlogConstant.LOGIN_PAGE;
    }

    /**
     * 功能描述: 用户登录
     *
     * @param loginDto   1
     * @param session    2
     * @param request    3
     * @param attributes 4
     * @return java.lang.String
     * @author xianzilei
     * @date 2019/12/2 8:44
     **/
    @RequestMapping("/login")
    public String login(LoginDto loginDto, HttpSession session, HttpServletRequest request,
                        RedirectAttributes attributes) {
        //用户名和密码校验
        User user = userService.checkUser(loginDto);
        if (user != null) {
            userService.updateLoginInfo(user, request.getRemoteAddr());
            user.setPassword(null);
            session.setAttribute("user", user);
            String path = StringUtils.isBlank(loginDto.getPath()) ? "/" : loginDto.getPath();
            return "redirect:" + path;
        } else {
            attributes.addFlashAttribute("path", loginDto.getPath());
            attributes.addFlashAttribute("username", loginDto.getUsername());
            attributes.addFlashAttribute("message", "用户名或密码错误");
            return "redirect:/user/toLoginPage";
        }
    }

    /**
     * 功能描述: 注销功能
     *
     * @param session 1
     * @return java.lang.String
     * @author xianzilei
     * @date 2019/12/4 19:09
     **/
    @GetMapping("/logout")
    public String logout(HttpSession session, HttpServletRequest request) {
        session.removeAttribute("user");
        String referer = request.getHeader("referer");
        return "redirect:" + referer;
    }
}
