package com.jicl.controller;

import com.jicl.entity.Blog;
import com.jicl.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Auther: xianzilei
 * @Date: 2019/11/30 09:28
 * @Description: 首页控制器
 */
@Controller
public class IndexController {

    @Autowired
    private BlogService blogService;

    @RequestMapping("/")
    public String index(Blog blog, @RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue =
            "10") Integer pageSize, Model model) {
        model.addAttribute("page", blogService.page(blog, pageNum, pageSize));
        model.addAttribute("types", blogService.getTopTypeList(6));
        model.addAttribute("tags", blogService.getTopTagList(10));
        model.addAttribute("recommendBlogs", blogService.getRecommendBlogs(8));
        return "hello";
    }
}
