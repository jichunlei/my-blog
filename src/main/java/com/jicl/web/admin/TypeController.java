package com.jicl.web.admin;

import com.jicl.pojo.Type;
import com.jicl.service.TypeService;
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
 * @Date: 2019/11/24 13:14
 * @Description: 类型管理控制层
 */
@Controller
@RequestMapping("/admin")
public class TypeController {

    @Autowired
    private TypeService typeService;

    /**
     * 分页查询类型列表信息
     *
     * @param pageable 1
     * @param model 2
     * @return: java.lang.String
     * @auther: xianzilei
     * @date: 2019/11/24 18:24
     **/
    @GetMapping("/types")
    public String types(@PageableDefault(size = 12, sort = {"id"},
            direction = Sort.Direction.DESC) Pageable pageable, Model model) {
        model.addAttribute("page", typeService.listType(pageable));
        return "admin/types";
    }

    /**
     * 跳转新增页面
     *
     * @param model 1
     * @return: java.lang.String
     * @auther: xianzilei
     * @date: 2019/11/24 18:24
     **/
    @GetMapping("/types/input")
    public String input(Model model) {
        model.addAttribute("type", new Type());
        return "admin/types-input";
    }

    /**
     * 新增类型信息
     *
     * @param type 1
     * @param result 2
     * @param attributes 3
     * @return: java.lang.String
     * @auther: xianzilei
     * @date: 2019/11/24 18:25
     **/
    @PostMapping("/types")
    public String post(@Valid Type type, BindingResult result, RedirectAttributes attributes) {
        Type type1 = typeService.getTypeByName(type.getName());
        if (type1 != null) {
            result.rejectValue("name","nameError","不能添加重复的分类");
        }
        if (result.hasErrors()) {
            return "admin/types-input";
        }
        Type t = typeService.saveType(type);
        if (t == null ) {
            attributes.addFlashAttribute("message", "新增失败");
        } else {
            attributes.addFlashAttribute("message", "新增成功");
        }
        return "redirect:/admin/types";
    }

    /**
     * 跳转编辑页面
     *
     * @param id 1
     * @param model 2
     * @return: java.lang.String
     * @auther: xianzilei
     * @date: 2019/11/24 18:28
     **/
    @GetMapping("/types/{id}/input")
    public String editInput(@PathVariable Long id, Model model){
        model.addAttribute("type",typeService.getType(id));
        return "admin/types-input";
    }

    /**
     * 修改分类信息
     *
     * @param type 1
     * @param result 2
     * @param attributes 3
     * @return: java.lang.String
     * @auther: xianzilei
     * @date: 2019/11/24 18:36
     **/
    @PostMapping("/types/{id}")
    public String editPost(@Valid Type type, BindingResult result,
                           @PathVariable Long id,RedirectAttributes attributes) {
        Type type1 = typeService.getTypeByName(type.getName());
        if (type1 != null) {
            result.rejectValue("name","nameError","不能添加重复的分类");
        }
        if (result.hasErrors()) {
            return "admin/types-input";
        }
        Type t = typeService.updateType(id,type);
        if (t == null ) {
            attributes.addFlashAttribute("message", "更新失败");
        } else {
            attributes.addFlashAttribute("message", "更新成功");
        }
        return "redirect:/admin/types";
    }

    /**
     * 删除分类
     *
     * @param id 1
     * @return: java.lang.String
     * @auther: xianzilei
     * @date: 2019/11/24 18:42
     **/
    @GetMapping("/types/{id}/delete")
    public String delete(@PathVariable Long id,RedirectAttributes attributes){
        typeService.deleteType(id);
        attributes.addFlashAttribute("message","删除成功");
        return "redirect:/admin/types";
    }
}
