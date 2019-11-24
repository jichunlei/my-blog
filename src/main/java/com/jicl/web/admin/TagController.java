package com.jicl.web.admin;

import com.jicl.pojo.Tag;
import com.jicl.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

/**
 * @Auther: xianzilei
 * @Date: 2019/11/24 20:01
 * @Description: 标签管理控制层
 */
@Controller
@RequestMapping("/admin")
public class TagController {
    @Autowired
    private TagService tagService;

    /**
     * 分页查询标签信息
     *
     * @param pageable 1
     * @param model 2
     * @return: java.lang.String
     * @auther: xianzilei
     * @date: 2019/11/24 20:03
     **/
    @GetMapping("/tags")
    public String tags(@PageableDefault(size = 8,sort = {"id"},direction = Sort.Direction.DESC)
                               Pageable pageable, Model model) {
        model.addAttribute("page",tagService.listTag(pageable));
        return "admin/tags";
    }

    /**
     * 跳转标签新增页面
     *
     * @param model 1
     * @return: java.lang.String
     * @auther: xianzilei
     * @date: 2019/11/24 20:03
     **/
    @GetMapping("/tags/input")
    public String input(Model model) {
        model.addAttribute("tag", new Tag());
        return "admin/tags-input";
    }

    /**
     * 跳转标签编辑页面
     *
     * @param id 1
     * @param model 2
     * @return: java.lang.String
     * @auther: xianzilei
     * @date: 2019/11/24 20:04
     **/
    @GetMapping("/tags/{id}/input")
    public String editInput(@PathVariable Long id, Model model) {
        model.addAttribute("tag", tagService.getTag(id));
        return "admin/tags-input";
    }

    /**
     * 新增标签
     *
     * @param tag 1
     * @param result 2
     * @param attributes 3
     * @return: java.lang.String
     * @auther: xianzilei
     * @date: 2019/11/24 20:04
     **/
    @PostMapping("/tags")
    public String post(@Valid Tag tag, BindingResult result, RedirectAttributes attributes) {
        Tag tag1 = tagService.getTagByName(tag.getName());
        if (tag1 != null) {
            result.rejectValue("name","nameError","不能添加重复的分类");
        }
        if (result.hasErrors()) {
            return "admin/tags-input";
        }
        Tag t = tagService.saveTag(tag);
        if (t == null ) {
            attributes.addFlashAttribute("message", "新增失败");
        } else {
            attributes.addFlashAttribute("message", "新增成功");
        }
        return "redirect:/admin/tags";
    }

    /**
     * 编辑标签
     *
     * @param tag 1
     * @param result 2
     * @param id 3
     * @param attributes 4
     * @return: java.lang.String
     * @auther: xianzilei
     * @date: 2019/11/24 20:05
     **/
    @PostMapping("/tags/{id}")
    public String editPost(@Valid Tag tag, BindingResult result,@PathVariable Long id, RedirectAttributes attributes) {
        Tag tag1 = tagService.getTagByName(tag.getName());
        if (tag1 != null) {
            result.rejectValue("name","nameError","不能添加重复的分类");
        }
        if (result.hasErrors()) {
            return "admin/tags-input";
        }
        Tag t = tagService.updateTag(id,tag);
        if (t == null ) {
            attributes.addFlashAttribute("message", "更新失败");
        } else {
            attributes.addFlashAttribute("message", "更新成功");
        }
        return "redirect:/admin/tags";
    }

    /**
     * 删除标签
     *
     * @param id 1
     * @param attributes 2
     * @return: java.lang.String
     * @auther: xianzilei
     * @date: 2019/11/24 20:05
     **/
    @GetMapping("/tags/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes) {
        tagService.deleteTag(id);
        attributes.addFlashAttribute("message", "删除成功");
        return "redirect:/admin/tags";
    }
}
