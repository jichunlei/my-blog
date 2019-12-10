package com.jicl.controller;

import com.jicl.service.BlogService;
import com.jicl.service.TagService;
import com.jicl.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 博客展示控制层
 *
 * @author : xianzilei
 * @date : 2019/12/9 10:03
 */
@Controller
public class BlogShowController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;

    /**
     * 功能描述: 博客详情查看
     *
     * @param id 1
     * @param model 2
     * @return java.lang.String
     * @author xianzilei
     * @date 2019/12/9 10:04
     **/
    @GetMapping("/blog/{id}")
    public String blog(@PathVariable Integer id, Model model) {
        model.addAttribute("blog", blogService.getBlogDetail(id));
        model.addAttribute("typeMap", typeService.getAllTypes());
        model.addAttribute("tagMap", tagService.getAllTags());
        return "blog";
    }
}
