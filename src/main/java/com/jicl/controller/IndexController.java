package com.jicl.controller;

import com.alibaba.fastjson.JSON;
import com.jicl.constant.BlogConstant;
import com.jicl.entity.Blog;
import com.jicl.service.BlogService;
import com.jicl.service.TagService;
import com.jicl.service.TypeService;
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

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;

    @RequestMapping("/")
    public String index(Blog blog, @RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue =
            "10") Integer pageSize, Model model) {
        model.addAttribute("page", blogService.page(blog, pageNum, pageSize));
        model.addAttribute("types", blogService.getTopTypeList(6));
        model.addAttribute("tags", blogService.getTopTagList(10));
        model.addAttribute("recommendBlogs", blogService.getRecommendBlogs(8));
        model.addAttribute("typeMap",typeService.getAllTypes());
        model.addAttribute("tagMap",tagService.getAllTags());
        return BlogConstant.INDEX_PAGE;
    }
}
