package com.jicl.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 登录控制器
 *
 * @author : xianzilei
 * @date : 2019/11/30 12:11
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    @RequestMapping
    public String login(){
        return "login";
    }
}
