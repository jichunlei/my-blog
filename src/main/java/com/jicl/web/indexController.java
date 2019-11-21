package com.jicl.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Auther: xianzilei
 * @Date: 2019/11/21 21:14
 * @Description: TODO
 */
@Controller
public class indexController {

    @GetMapping("/")
    public String index(){
        int i=9/0;
        return "index";
    }
}
