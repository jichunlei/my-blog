package com.jicl.controller;

import com.jicl.constant.BlogConstant;
import com.jicl.dto.LoginDto;
import com.jicl.entity.User;
import com.jicl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @RequestMapping("/login")
    public String login(LoginDto loginDto, HttpSession session, HttpServletRequest request, RedirectAttributes attributes){
        //用户名和密码校验
        User user = userService.checkUser(loginDto);
        if (user != null) {
            userService.updateLoginInfo(user,request.getRemoteAddr());
            user.setPassword(null);
            session.setAttribute("user",user);
            return BlogConstant.INDEX_PAGE;
        } else {
            attributes.addFlashAttribute("message", "用户名或密码错误");
            return "redirect:/admin";
        }
    }
}
