package com.jicl.controller.admin;

import com.jicl.entity.Tag;
import com.jicl.entity.TagExample;
import com.jicl.service.TagService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * 标签管理：管理员
 *
 * @author : xianzilei
 * @date : 2019/12/19 16:20
 */
@Slf4j
@Controller
@RequestMapping("/admin")
public class TagController {

    @Autowired
    private TagService tagService;

    /**
     * 功能描述: 标签管理
     *
     * @param pageNum  1
     * @param pageSize 2
     * @param model    3
     * @return java.lang.String
     * @author xianzilei
     * @date 2019/12/24 9:40
     **/
    @GetMapping("/tags")
    public String types(@RequestParam(defaultValue = "1") Integer pageNum,
                        @RequestParam(defaultValue = "10") Integer pageSize, Model model) {
        TagExample tagExample = new TagExample();
        tagExample.createCriteria().andDelFlagEqualTo(false);
        tagExample.setOrderByClause("create_time");
        model.addAttribute("page", tagService.page(tagExample, pageNum, pageSize));
        return "admin/tags";
    }

    /**
     * 功能描述: 新增标签界面
     *
     * @param model 1
     * @return java.lang.String
     * @author xianzilei
     * @date 2019/12/24 14:05
     **/
    @GetMapping("/tags/input")
    public String input(Model model) {
        model.addAttribute("tag", new Tag());
        return "admin/tags-input";
    }

    /**
     * 功能描述: 编辑标签界面
     *
     * @param id    1
     * @param model 2
     * @return java.lang.String
     * @author xianzilei
     * @date 2019/12/24 14:07
     **/
    @GetMapping("/tags/{id}/input")
    public String editInput(@PathVariable Integer id, Model model) {
        model.addAttribute("tag", tagService.findOne(id));
        return "admin/tags-input";
    }

    /***
     * 功能描述: 提交标签信息
     *
     * @param tag 1
     * @param result 2
     * @param attributes 3
     * @return java.lang.String
     * @author xianzilei
     * @date 2019/12/24 14:07
     **/
    @PostMapping("/tags")
    public String post(Tag tag, BindingResult result, RedirectAttributes attributes) {
        Tag tag1 = tagService.findTagByName(tag.getTagName());
        if (tag1 != null) {
            result.rejectValue("tagName", "nameError", "不能添加重复的分类");
        }
        if (result.hasErrors()) {
            return "admin/tags-input";
        }
        try {
            tagService.saveTag(tag);
            attributes.addFlashAttribute("message", "更新成功");
        } catch (Exception e) {
            log.error("更新失败", e);
            attributes.addFlashAttribute("message", "更新失败");
        }
        return "redirect:/admin/tags";
    }

    /**
     * 功能描述: 删除标签信息
     *
     * @param id         1
     * @param attributes 2
     * @return java.lang.String
     * @author xianzilei
     * @date 2019/12/24 14:11
     **/
    @GetMapping("/tags/{id}/delete")
    public String deleteTag(@PathVariable Integer id, RedirectAttributes attributes) {
        try {
            attributes.addFlashAttribute("message", "删除成功");
            tagService.deleteTag(id);
        } catch (Exception e) {
            log.error("删除失败", e);
            attributes.addFlashAttribute("message", "删除失败");
        }
        return "redirect:/admin/tags";
    }
}
