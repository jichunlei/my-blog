package com.jicl.controller;

import com.jicl.constant.BlogConstant;
import com.jicl.service.AnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 公告管理
 *
 * @author : xianzilei
 * @date : 2019/12/17 19:47
 */
@Controller
public class AnnouncementController {

    @Autowired
    private AnnouncementService announcementService;

    @GetMapping("/toAnncPage")
    public String toAnncPage(@RequestParam(required = false, defaultValue = "1") Integer pageNum,
                             @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                             Model model) {
        model.addAttribute("page", announcementService.page(pageNum, pageSize));
        return BlogConstant.ANNOUNCEMENT_PAGE;
    }

}
