package com.jicl.controller;

import com.jicl.entity.User;
import com.jicl.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

/**
 * 回复管理控制层
 *
 * @author : xianzilei
 * @date : 2019/12/11 17:51
 */
@Controller
public class ReplyController {

    @Autowired
    private ReplyService replyService;

    @PostMapping("/replys")
    public String addReplys(Integer commentId, Integer blogId, String content,
                            Integer repliedUserId, HttpSession session) {
        User user = (User) session.getAttribute("user");
        //新增评论
        replyService.addReplys(commentId, blogId, content, user,repliedUserId);
        return "redirect:/comments/" + blogId;
    }
}
