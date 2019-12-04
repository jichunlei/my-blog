package com.jicl.controller;

import com.jicl.pojo.TopType;
import com.jicl.service.BlogService;
import com.jicl.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 博客类型管理控制类
 *
 * @author : xianzilei
 * @date : 2019/11/30 12:07
 */
@Controller
public class TypeController {
    @Autowired
    private TypeService typeService;

    @Autowired
    private BlogService blogService;

    @GetMapping("/types/{id}")
    public String types(@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue =
            "6") Integer pageSize, @PathVariable Integer id, Model model) {
        List<TopType> types = blogService.getTopTypeList(10000);
        if (id == -1) {
            id = types.get(0).getTypeId();
        }
        model.addAttribute("types", types);
//        model.addAttribute("page", blogService.listBlog(pageable, blogQuery));
        model.addAttribute("activeTypeId", id);
        return "types";
    }
}
