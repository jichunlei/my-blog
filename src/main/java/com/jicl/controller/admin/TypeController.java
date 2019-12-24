package com.jicl.controller.admin;

import com.jicl.entity.Type;
import com.jicl.entity.TypeExample;
import com.jicl.service.TypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * 类型管理：管理员
 *
 * @author : xianzilei
 * @date : 2019/12/19 16:20
 */
@Slf4j
@Controller
@RequestMapping("/admin")
public class TypeController {
    @Autowired
    private TypeService typeService;

    /**
     * 功能描述: 分类管理
     *
     * @param pageNum  1
     * @param pageSize 2
     * @param model    3
     * @return java.lang.String
     * @author xianzilei
     * @date 2019/12/24 9:40
     **/
    @GetMapping("/types")
    public String types(@RequestParam(defaultValue = "1") Integer pageNum,
                        @RequestParam(defaultValue = "10") Integer pageSize, Model model) {
        TypeExample typeExample = new TypeExample();
        typeExample.createCriteria().andDelFlagEqualTo(false);
        typeExample.setOrderByClause("create_time");
        model.addAttribute("page", typeService.page(typeExample, pageNum, pageSize));
        return "admin/types";
    }

    /**
     * 功能描述: 新增博客类型
     *
     * @param model 1
     * @return java.lang.String
     * @author xianzilei
     * @date 2019/12/24 9:47
     **/
    @GetMapping("/types/input")
    public String addInput(Model model) {
        model.addAttribute("type", new Type());
        return "admin/types-input";
    }

    /**
     * 功能描述: 编辑博客类型信息
     *
     * @param id    1
     * @param model 2
     * @return java.lang.String
     * @author xianzilei
     * @date 2019/12/24 10:18
     **/
    @GetMapping("/types/{id}/input")
    public String editInput(@PathVariable Integer id, Model model) {
        model.addAttribute("type", typeService.findOne(id));
        return "admin/types-input";
    }

    /**
     * 功能描述: 更新博客类型信息
     *
     * @param type       1
     * @param result     2
     * @param attributes 3
     * @return java.lang.String
     * @author xianzilei
     * @date 2019/12/24 10:29
     **/
    @PostMapping("/types")
    public String post(Type type, BindingResult result, RedirectAttributes attributes) {
        Type type1 = typeService.findTypeByName(type.getTypeName());
        if (type1 != null) {
            result.rejectValue("typeName", "nameError", "不能添加重复的分类");
        }
        if (result.hasErrors()) {
            return "admin/types-input";
        }
        try {
            typeService.saveType(type);
            attributes.addFlashAttribute("message", "更新成功");
        } catch (Exception e) {
            log.error("更新失败", e);
            attributes.addFlashAttribute("message", "更新失败");
        }
        return "redirect:/admin/types";
    }

    /**
     * 功能描述: 删除分类信息
     *
     * @param id         1
     * @param attributes 2
     * @return java.lang.String
     * @author xianzilei
     * @date 2019/12/24 12:27
     **/
    @GetMapping("/types/{id}/delete")
    public String deleteType(@PathVariable Integer id, RedirectAttributes attributes) {
        try {
            typeService.deleteType(id);
            attributes.addFlashAttribute("message", "删除成功");
        } catch (Exception e) {
            log.error("删除失败", e);
            attributes.addFlashAttribute("message", "删除失败");
        }
        return "redirect:/admin/types";
    }
}


