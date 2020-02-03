package com.jicl.controller;

import com.jicl.constant.BlogConstant;
import com.jicl.entity.BlogExample;
import com.jicl.entity.User;
import com.jicl.es.EsBlogService;
import com.jicl.service.BlogService;
import com.jicl.service.TagService;
import com.jicl.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * 首页控制器
 *
 * @author xianzilei
 * @date 2019/11/30 09:28
 */
@Controller
public class IndexController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;

    @Autowired
    private EsBlogService esBlogService;

    /**
     * 功能描述: 首页
     *
     * @param pageNum  1
     * @param pageSize 2
     * @param model    3
     * @return java.lang.String
     * @author xianzilei
     * @date 2019/12/4 18:34
     **/
    @RequestMapping("/")
    public String index(@RequestParam(defaultValue = "1") Integer pageNum,
                        @RequestParam(defaultValue =
                                "10") Integer pageSize, Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        Integer userId = user == null ? null : user.getUserId();
        BlogExample blogExample = new BlogExample();
        blogExample.createCriteria().andPublishedEqualTo(true);
        blogExample.setOrderByClause("create_time desc");
        model.addAttribute("page", blogService.page(blogExample, pageNum, pageSize, userId));
        model.addAttribute("types", blogService.getTopTypeList(6));
        model.addAttribute("tags", blogService.getTopTagList(10));
        model.addAttribute("recommendBlogs", blogService.getRecommendBlogs(8));
        model.addAttribute("typeMap", typeService.getAllTypes());
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
     * @param pageNum   2
     * @param pageSize  3
     * @param model     4
     * @return java.lang.String
     * @author xianzilei
     * @date 2019/12/4 8:47
     **/
    @GetMapping("/search")
    public String search(String searchKey, @RequestParam(defaultValue = "1") Integer pageNum,
                         @RequestParam(defaultValue = "10") Integer pageSize, Model model,
                         HttpSession session) {
        User user = (User) session.getAttribute("user");
        Integer userId = user == null ? null : user.getUserId();
        model.addAttribute("page", esBlogService.search(searchKey,
                pageNum - 1, pageSize, userId));
        model.addAttribute("typeMap", typeService.getAllTypes());
        model.addAttribute("searchKey", searchKey);
        return BlogConstant.SEARCH_PAGE;
    }

    /**
     * 功能描述: 点赞/取消点赞
     *
     * @param blogId  1
     * @param flag    2
     * @param session 3
     * @return java.lang.String
     * @author xianzilei
     * @date 2020/2/1 20:24
     **/
    @PostMapping("/thumbsUp")
    @ResponseBody
    public String thumbsUp(Integer blogId, Boolean flag, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "toLogin";
        } else {
            if (flag) {
                blogService.addThumbsUp(blogId, user.getUserId());
            } else {
                blogService.cancelThumbsUp(blogId, user.getUserId());
            }
        }
        return "success";
    }
}
