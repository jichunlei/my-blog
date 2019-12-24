package com.jicl.controller;

import com.jicl.constant.BlogConstant;
import com.jicl.service.AnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 公告管理
 *
 * @author : xianzilei
 * @date : 2019/12/17 19:47
 */
@Controller
public class AnnouncementShowController {

    @Autowired
    private AnnouncementService announcementService;

    @GetMapping("/toAnncPage")
    public String toAnncPage(@RequestParam(required = false, defaultValue = "1") Integer pageNum,
                             @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                             Model model) {
        model.addAttribute("page", announcementService.page(pageNum, pageSize));
        return BlogConstant.ANNOUNCEMENT_PAGE;
    }

    /**
     * 功能描述: 博客详情查看
     *
     * @param id    1
     * @param model 2
     * @return java.lang.String
     * @author xianzilei
     * @date 2019/12/9 10:04
     **/
    @GetMapping("/annc/{id}")
    public String getAnnc(@PathVariable Integer id, Model model) {
        model.addAttribute("annc", announcementService.getAnnc(id));
        return BlogConstant.ANNOUNCEMENT_DETAIL;
    }

}
