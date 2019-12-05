package com.jicl.controller;

import com.jicl.constant.BlogConstant;
import com.jicl.entity.BlogExample;
import com.jicl.pojo.TopTag;
import com.jicl.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 标签管理
 *
 * @author : xianzilei
 * @date : 2019/12/5 09:01
 */
@Controller
public class TagShowController {

    @Autowired
    private BlogService blogService;

    /**
     * 功能描述: 标签页
     *
     * @param pageNum  1
     * @param pageSize 2
     * @param id       3
     * @param model    4
     * @return java.lang.String
     * @author xianzilei
     * @date 2019/12/5 19:43
     **/
    @GetMapping("/tags/{id}")
    public String tags(@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue =
            "6") Integer pageSize, @PathVariable Integer id, Model model) {
        List<TopTag> tags = blogService.getTopTagList(10000);
        if (id == -1) {
            id = tags.get(0).getTagId();
        }
        model.addAttribute("tags", tags);
        BlogExample blogExample = new BlogExample();
        blogExample.createCriteria().andTagIdStrLike("%-" + id + "-%");
        blogExample.setOrderByClause("blog_views desc");
        model.addAttribute("page", blogService.page(blogExample, pageNum, pageSize));
        model.addAttribute("activeTagId", id);
        return BlogConstant.TAGS_PAGE;
    }
}
