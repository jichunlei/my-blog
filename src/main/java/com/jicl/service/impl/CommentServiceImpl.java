package com.jicl.service.impl;

import com.github.pagehelper.PageHelper;
import com.jicl.mapper.CommentExtendMapper;
import com.jicl.mapper.ReplyExtendMapper;
import com.jicl.pojo.CommentExtend;
import com.jicl.pojo.ReplyExtend;
import com.jicl.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 评论管理服务层实现
 *
 * @author : xianzilei
 * @date : 2019/12/9 18:16
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentExtendMapper commentExtendMapper;

    @Autowired
    private ReplyExtendMapper replyExtendMapper;

    /**
     * 功能描述: 获取评论信息列表
     *
     * @param blogId   1
     * @param pageNum  2
     * @param pageSize 3
     * @return java.util.List<com.jicl.pojo.CommentExtend>
     * @author xianzilei
     * @date 2019/12/9 18:50
     **/
    @Override
    public List<CommentExtend> getComments(Integer blogId, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize, "comment_time desc");
        List<CommentExtend> commentExtends = commentExtendMapper.getComments(blogId);
        for (CommentExtend commentExtend : commentExtends) {
            List<ReplyExtend> replyExtendList = replyExtendMapper.getReplys(commentExtend.getCommentId());
            commentExtend.setReplyNums(replyExtendList.size());
            commentExtend.setReplyList(replyExtendList);
        }
        return commentExtends;
    }
}
