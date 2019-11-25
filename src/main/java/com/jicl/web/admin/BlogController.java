package com.jicl.web.admin;

import com.jicl.pojo.Blog;
import com.jicl.service.BlogService;
import com.jicl.service.TagService;
import com.jicl.service.TypeService;
import com.jicl.vo.BlogQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Auther: xianzilei
 * @Date: 2019/11/24 11:42
 * @Description: 博客管理控制类
 */
@Controller
@RequestMapping("/admin")
public class BlogController {

    private static final String INPUT = "admin/blogs-input";
    private static final String LIST = "admin/blogs";
    private static final String REDIRECT_LIST = "redirect:/admin/blogs";

    @Autowired
    private BlogService blogService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private TagService tagService;

    /**
     * 根据条件分页查询博客列表
     *
     * @param pageable 1
     * @param blog     2
     * @param model    3
     * @return: java.lang.String
     * @auther: xianzilei
     * @date: 2019/11/24 21:08
     **/
    @GetMapping("/blogs")
    public String blogs(@PageableDefault(size = 8, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable, BlogQuery blog, Model model) {
        model.addAttribute("types", typeService.listType());
        model.addAttribute("page", blogService.listBlog(pageable, blog));
        return LIST;
    }

    /**
     * 返回片段
     *
     * @param pageable 1
     * @param blog     2
     * @param model    3
     * @return: java.lang.String
     * @auther: xianzilei
     * @date: 2019/11/24 21:26
     **/
    @PostMapping("/blogs/search")
    public String search(@PageableDefault(size = 8, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable,
                         BlogQuery blog, Model model) {
        model.addAttribute("page", blogService.listBlog(pageable, blog));
        return "admin/blogs :: blogList";
    }

    /**
     * 功能描述: 跳转博客新增页面
     *
     * @param model 1
     * @return: java.lang.String
     * @auther: xianzilei
     * @date: 2019/11/25 8:51
     **/
    @GetMapping("/blogs/input")
    public String input(Model model) {
        setTypeAndTag(model);
        model.addAttribute("blog", new Blog());
        return INPUT;
    }

    /**
     * 功能描述: 组装标签和分类信息
     *
     * @param model 1
     * @return: void
     **/
    private void setTypeAndTag(Model model) {
        model.addAttribute("types", typeService.listType());
        model.addAttribute("tags", tagService.listTag());
    }
}
