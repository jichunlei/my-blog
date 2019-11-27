package com.jicl.web;

import com.jicl.pojo.Comment;
import com.jicl.pojo.User;
import com.jicl.service.BlogService;
import com.jicl.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import javax.servlet.http.HttpSession;

/**
 * @Auther: xianzilei
 * @Date: 2019/11/27 19:21
 * @Description: 评论管理控制层
 */
@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private BlogService blogService;

    @Value("${comment.avatar}")
    private String avatar;

    /**
     * 功能描述: 根据博客id获取评论信息列表
     *
     * @param blogId 1
     * @param model 2
     * @return: java.lang.String
     * @auther: xianzilei
     * @date: 2019/11/27 8:50
     **/
    @GetMapping("/comments/{blogId}")
    public String comments(@PathVariable Long blogId, Model model) {
        model.addAttribute("comments", commentService.listCommentByBlogId(blogId));
        return "blog :: commentList";
    }

    /**
     * 功能描述: 提交评论
     *
     * @param comment 1
     * @param session 2
     * @return: java.lang.String
     * @auther: xianzilei
     * @date: 2019/11/27 8:51
     **/
    @PostMapping("/comments")
    public String post(Comment comment, HttpSession session) {
        Long blogId = comment.getBlog().getId();
        comment.setBlog(blogService.getBlog(blogId));
        User user = (User) session.getAttribute("user");
        if (user != null) {
            comment.setAvatar(user.getAvatar());
            comment.setAdminComment(true);
        } else {
            comment.setAvatar(avatar);
        }
        commentService.saveComment(comment);
        return "redirect:/comments/" + blogId;
    }
}
