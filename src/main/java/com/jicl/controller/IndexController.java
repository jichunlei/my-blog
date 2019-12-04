package com.jicl.controller;

import com.jicl.constant.BlogConstant;
import com.jicl.service.BlogService;
import com.jicl.service.TagService;
import com.jicl.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

    /**
     * 功能描述: 首页
     *
     * @param pageNum 1
     * @param pageSize 2
     * @param model 3
     * @return java.lang.String
     * @author xianzilei
     * @date 2019/12/4 18:34
     **/
    @RequestMapping("/")
    public String index(@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue =
            "6") Integer pageSize, Model model) {
        model.addAttribute("page", blogService.page(null, pageNum, pageSize));
        model.addAttribute("types", blogService.getTopTypeList(6));
        model.addAttribute("tags", blogService.getTopTagList(10));
        model.addAttribute("recommendBlogs", blogService.getRecommendBlogs(8));
        model.addAttribute("typeMap",typeService.getAllTypes());
//        model.addAttribute("tagMap",tagService.getAllTags());
        return BlogConstant.INDEX_PAGE;
    }

    /**
     * 功能描述: 查询最新博客
     *
     * @param model 1
     * @return java.lang.String
     * @author xianzilei
     * @date 2019/12/2 21:33
     **/
    @GetMapping("/footer/newblog")
    public String newblogs(Model model) {
        model.addAttribute("newblogs", blogService.getLastUpdateBlogTop(3));
        return "_fragments :: newblogList";
    }

    /**
     * 功能描述: 搜索标题关键字
     *
     * @param searchKey 1
     * @param pageNum 2
     * @param pageSize 3
     * @param model 4
     * @return java.lang.String
     * @author xianzilei
     * @date 2019/12/4 8:47
     **/
    @PostMapping("/search")
    public String search(String searchKey,@RequestParam(defaultValue = "1") Integer pageNum,
                         @RequestParam(defaultValue =
            "6") Integer pageSize, Model model) {
        model.addAttribute("page", blogService.page(searchKey, pageNum, pageSize));
        model.addAttribute("typeMap",typeService.getAllTypes());
        model.addAttribute("searchKey", searchKey);
        return BlogConstant.SEARCH_PAGE;
    }
}
