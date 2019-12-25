package com.jicl.controller.user;

import com.jicl.entity.Blog;
import com.jicl.entity.BlogExample;
import com.jicl.entity.Type;
import com.jicl.entity.User;
import com.jicl.service.BlogService;
import com.jicl.service.TagService;
import com.jicl.service.TypeService;
import com.jicl.vo.BlogVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 博客管理
 *
 * @author : xianzilei
 * @date : 2019/12/17 20:22
 */
@Slf4j
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;

    @Autowired
    private BlogService blogService;

    /**
     * 功能描述: 个人中心
     *
     * @return java.lang.String
     * @author xianzilei
     * @date 2019/12/18 9:34
     **/
    @RequestMapping
    public String index() {
        return "user/index";
    }

    /**
     * 功能描述: 博客列表查询
     *
     * @param pageNum  1
     * @param pageSize 2
     * @param model    3
     * @return java.lang.String
     * @author xianzilei
     * @date 2019/12/18 15:03
     **/
    @GetMapping("/blogs")
    public String blogs(@RequestParam(defaultValue = "1") Integer pageNum,
                        @RequestParam(defaultValue = "10") Integer pageSize, Model model,HttpSession session) {
        User user = (User) session.getAttribute("user");
        List<Type> typeList = typeService.getAll();
        model.addAttribute("types", typeList);
        model.addAttribute("typeMap", typeList.stream().collect(Collectors.toMap(Type::getTypeId, Type::getTypeName,
                (key1, key2) -> key2)));
        model.addAttribute("tags", tagService.getAll());
        BlogExample blogExample = new BlogExample();
        blogExample.createCriteria().andUserIdEqualTo(user.getUserId());
        blogExample.setOrderByClause("update_time desc");
        model.addAttribute("page", blogService.page(blogExample, pageNum, pageSize));
        return "user/blogs";
    }

    /**
     * 功能描述: 博客搜索
     *
     * @param pageNum 1
     * @param pageSize 2
     * @param blogTitle 3
     * @param typeId 4
     * @param tagId 5
     * @param model 6
     * @param session 7
     * @return java.lang.String
     * @author xianzilei
     * @date 2019/12/25 12:08
     **/
    @PostMapping("/blogs/search")
    public String search(@RequestParam(defaultValue = "1") Integer pageNum,
                         @RequestParam(defaultValue = "10") Integer pageSize,
                         String blogTitle, Integer typeId, Integer tagId, Model model,HttpSession session) {
        User user = (User) session.getAttribute("user");
        BlogExample blogExample = new BlogExample();
        BlogExample.Criteria criteria = blogExample.createCriteria();
        criteria.andUserIdEqualTo(user.getUserId());
        if (StringUtils.isNotBlank(blogTitle)) {
            criteria.andBlogTitleLike("%" + blogTitle + "%");
        }
        if (typeId != null) {
            criteria.andTypeIdEqualTo(typeId);
        }
        if (tagId != null) {
            criteria.andTagIdStrLike("%," + tagId + ",%");
        }
        blogExample.setOrderByClause("update_time desc");
        model.addAttribute("page", blogService.page(blogExample, pageNum, pageSize));
        model.addAttribute("typeMap", typeService.getAllTypes());
        return "user/blogs :: blogList";
    }

    /**
     * 功能描述: 跳转博客新增页面
     *
     * @param model 1
     * @return java.lang.String
     * @author xianzilei
     * @date 2019/12/18 15:43
     **/
    @GetMapping("/blogs/input")
    public String input(Model model) {
        model.addAttribute("types", typeService.getAll());
        model.addAttribute("tags", tagService.getAll());
        model.addAttribute("blog", new BlogVo());
        return "user/blogs-input";
    }

    /**
     * 功能描述: 进入博客编辑页面
     *
     * @param id    1
     * @param model 2
     * @return java.lang.String
     * @author xianzilei
     * @date 2019/12/18 18:53
     **/
    @GetMapping("/blogs/{id}/input")
    public String editInput(@PathVariable Integer id, Model model) {
        model.addAttribute("types", typeService.getAll());
        model.addAttribute("tags", tagService.getAll());
        Blog blog = blogService.findOne(id);
        model.addAttribute("blog", blog);
        return "user/blogs-input";
    }

    /**
     * 功能描述: 提交博客信息
     *
     * @param blog 1
     * @param attributes 2
     * @param session 3
     * @return java.lang.String
     * @author xianzilei
     * @date 2019/12/18 19:12
     **/
    @PostMapping("/blogs")
    public String updateBlog(Blog blog, RedirectAttributes attributes, HttpSession session) {
        User user = (User) session.getAttribute("user");
        blog.setUserId(user.getUserId());
        try {
            if (blog.getBlogId() == null) {
                blogService.addBlog(blog);
            } else {
                blogService.updateBlog(blog);
            }
            attributes.addFlashAttribute("message", "操作成功");
        } catch (Exception e) {
            log.error("更新博客信息报错：错误信息如下", e);
            attributes.addFlashAttribute("message", "操作失败");
        }
        return "redirect:/user/blogs";
    }

    /**
     * 功能描述: 删除博客
     *
     * @param id 1
     * @param attributes 2
     * @return java.lang.String
     * @author xianzilei
     * @date 2019/12/19 8:37
     **/
    @GetMapping("/blogs/{id}/delete")
    public String delete(@PathVariable Integer id,RedirectAttributes attributes) {
        blogService.deleteBlog(id);
        attributes.addFlashAttribute("message", "删除成功");
        return "redirect:/user/blogs";
    }

    /**
     * 功能描述: 个人博客归档
     *
     * @param session 1
     * @param model 2
     * @return java.lang.String
     * @author xianzilei
     * @date 2019/12/19 17:55
     **/
    @GetMapping("/archives")
    public String archives(HttpSession session,Model model) {
        User user = (User) session.getAttribute("user");
        model.addAttribute("archiveMap", blogService.archiveBlog(user.getUserId()));
        model.addAttribute("blogCount", blogService.countBlog(user.getUserId()));
        return "user/archives";
    }
}
