package com.jicl.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Auther: xianzilei
 * @Date: 2019/11/21 21:14
 * @Description: 关于我展示控制层
 */
@Controller
public class AboutShowController {

    @GetMapping("/about")
    public String about() {
        return "about";
    }
}
