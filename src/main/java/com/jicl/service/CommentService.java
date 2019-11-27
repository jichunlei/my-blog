package com.jicl.service;

import com.jicl.pojo.Comment;

import java.util.List;

/**
 * @Auther: xianzilei
 * @Date: 2019/11/27 08:37
 * @Description: 评论管理服务接口
 */
public interface CommentService {

    /**
     * 功能描述: 根据博客id查询评论信息列表
     *
     * @param blogId 1
     * @return: java.util.List<com.jicl.pojo.Comment>
     * @auther: xianzilei
     * @date: 2019/11/27 8:53
     **/
    List<Comment> listCommentByBlogId(Long blogId);

    /**
     * 功能描述: 新增评论
     *
     * @param comment 1
     * @return: com.jicl.pojo.Comment
     * @auther: xianzilei
     * @date: 2019/11/27 9:03
     **/
    Comment saveComment(Comment comment);
}
