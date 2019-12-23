package com.jicl.controller.admin;

import com.jicl.entity.BlogExample;
import com.jicl.entity.Type;
import com.jicl.service.BlogService;
import com.jicl.service.TagService;
import com.jicl.service.TypeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 博客管理：管理员
 *
 * @author : xianzilei
 * @date : 2019/12/19 16:20
 */
@Slf4j
@Controller
@RequestMapping("/admin")
public class AdminBlogController {

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;

    @Autowired
    private BlogService blogService;

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
                        @RequestParam(defaultValue = "10") Integer pageSize, Model model) {
        List<Type> typeList = typeService.getAll();
        model.addAttribute("typeMap", typeList.stream().collect(Collectors.toMap(Type::getTypeId, Type::getTypeName,
                (key1, key2) -> key2)));
        model.addAttribute("types", typeList);
        BlogExample blogExample = new BlogExample();
        blogExample.setOrderByClause("create_time desc");
        model.addAttribute("tags", tagService.getAll());
        model.addAttribute("page", blogService.page(blogExample, pageNum, pageSize));
        return "admin/blogs";
    }

    @PostMapping("/blogs/search")
    public String search(@RequestParam(defaultValue = "1") Integer pageNum,
                         @RequestParam(defaultValue = "10") Integer pageSize,
                         String blogTitle, Integer typeId, Integer tagId, Model model) {
        BlogExample blogExample = new BlogExample();
        BlogExample.Criteria criteria = blogExample.createCriteria();
        if (typeId != null) {
            criteria.andTypeIdEqualTo(typeId);
        }
        if (tagId != null) {
            criteria.andTagIdStrLike("%," + tagId + ",%");
        }
        if (StringUtils.isNotBlank(blogTitle)) {
            criteria.andBlogTitleLike("%" + blogTitle + "%");
        }
        blogExample.setOrderByClause("create_time desc");
        model.addAttribute("typeMap", typeService.getAllTypes());
        model.addAttribute("page", blogService.page(blogExample, pageNum, pageSize));
        return "admin/blogs :: blogList";
    }

    /**
     * 功能描述: 驳回博客
     *
     * @param id         1
     * @param attributes 2
     * @return java.lang.String
     * @author xianzilei
     * @date 2019/12/23 16:30
     **/
    @GetMapping("/blogs/{id}/reject")
    public String rejectBlog(@PathVariable Integer id, RedirectAttributes attributes) {
        blogService.rejectBlog(id);
        attributes.addFlashAttribute("message", "驳回成功");
        return "redirect:/admin/blogs";
    }

    /**
     * 功能描述: 删除博客
     *
     * @param id         1
     * @param attributes 2
     * @return java.lang.String
     * @author xianzilei
     * @date 2019/12/19 8:37
     **/
    @GetMapping("/blogs/{id}/delete")
    public String delete(@PathVariable Integer id, RedirectAttributes attributes) {
        blogService.deleteBlog(id);
        attributes.addFlashAttribute("message", "删除成功");
        return "redirect:/user/blogs";
    }
}
