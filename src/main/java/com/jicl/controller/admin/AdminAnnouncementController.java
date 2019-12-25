package com.jicl.controller.admin;

import com.jicl.entity.Announcement;
import com.jicl.entity.AnnouncementExample;
import com.jicl.service.AnnouncementService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

/**
 * 公告管理：管理员
 *
 * @author : xianzilei
 * @date : 2019/12/25 10:54
 */
@Slf4j
@Controller
@RequestMapping("/admin")
public class AdminAnnouncementController {

    @Autowired
    private AnnouncementService announcementService;

    /**
     * 功能描述: 跳转公告列表
     *
     * @param pageNum  1
     * @param pageSize 2
     * @param model    3
     * @return java.lang.String
     * @author xianzilei
     * @date 2019/12/25 12:07
     **/
    @RequestMapping("/toAnncPage")
    public String toAnncPage(@RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize, Model model) {
        AnnouncementExample announcementExample = new AnnouncementExample();
        announcementExample.createCriteria().andDelFlagEqualTo(false);
        announcementExample.setOrderByClause("create_time desc");
        model.addAttribute("page", announcementService.page(announcementExample, pageNum, pageSize));
        return "admin/announcements";
    }

    /**
     * 功能描述: 搜索服务
     *
     * @param pageNum   1
     * @param pageSize  2
     * @param anncTitle 3
     * @param topFlag   4
     * @param published 5
     * @param model     6
     * @return java.lang.String
     * @author xianzilei
     * @date 2019/12/25 13:47
     **/
    @PostMapping("/annc/search")
    public String search(@RequestParam(defaultValue = "1") Integer pageNum,
                         @RequestParam(defaultValue = "10") Integer pageSize,
                         String anncTitle, Boolean topFlag, Boolean published, Model model) {
        AnnouncementExample announcementExample = new AnnouncementExample();
        AnnouncementExample.Criteria criteria = announcementExample.createCriteria().andDelFlagEqualTo(false);
        announcementExample.setOrderByClause("create_time desc");
        if (StringUtils.isNotBlank(anncTitle)) {
            criteria.andAnncTitleLike("%" + anncTitle + "%");
        }
        if (topFlag != null) {
            criteria.andTopFlagEqualTo(topFlag);
        }
        if (published != null) {
            criteria.andPublishedEqualTo(published);
        }
        model.addAttribute("page", announcementService.page(announcementExample, pageNum, pageSize));
        return "admin/announcements:: anncList";
    }

    /**
     * 功能描述: 跳转公告新增页面
     *
     * @param model 1
     * @return java.lang.String
     * @author xianzilei
     * @date 2019/12/18 15:43
     **/
    @GetMapping("/annc/input")
    public String input(Model model) {
        model.addAttribute("announcement", new Announcement());
        return "admin/annc-input";
    }

    /**
     * 功能描述: 进入公告编辑页面
     *
     * @param id    1
     * @param model 2
     * @return java.lang.String
     * @author xianzilei
     * @date 2019/12/18 18:53
     **/
    @GetMapping("/annc/{id}/input")
    public String editInput(@PathVariable Integer id, Model model) {
        Announcement announcement = announcementService.findOne(id);
        model.addAttribute("announcement", announcement);
        return "admin/annc-input";
    }

    /**
     * 功能描述: 提交公告信息
     *
     * @param announcement 1
     * @param attributes   2
     * @param session      3
     * @return java.lang.String
     * @author xianzilei
     * @date 2019/12/25 16:39
     **/
    @PostMapping("/annc")
    public String post(Announcement announcement, RedirectAttributes attributes, HttpSession session) {
        try {
            announcementService.saveAnnouncement(announcement);
            attributes.addFlashAttribute("message", "操作成功");
        } catch (Exception e) {
            log.error("更新公告信息报错：错误信息如下", e);
            attributes.addFlashAttribute("message", "操作失败");
        }
        return "redirect:/admin/toAnncPage";
    }

    /**
     * 功能描述: 删除公告
     *
     * @param id         1
     * @param attributes 2
     * @return java.lang.String
     * @author xianzilei
     * @date 2019/12/25 16:39
     **/
    @GetMapping("/annc/{id}/delete")
    public String delete(@PathVariable Integer id, RedirectAttributes attributes) {
        try {
            announcementService.deleteAnnouncement(id);
            attributes.addFlashAttribute("message", "删除成功");
        } catch (Exception e) {
            log.error("删除公告信息报错：错误信息如下", e);
            attributes.addFlashAttribute("message", "操作失败");
        }
        return "redirect:/admin/toAnncPage";
    }
}
