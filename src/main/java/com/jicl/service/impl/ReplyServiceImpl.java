package com.jicl.service.impl;

import com.github.pagehelper.PageHelper;
import com.jicl.entity.Reply;
import com.jicl.entity.User;
import com.jicl.mapper.ReplyExtendMapper;
import com.jicl.mapper.ReplyMapper;
import com.jicl.pojo.ReplyExtend;
import com.jicl.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 回复服务实现类
 *
 * @author : xianzilei
 * @date : 2019/12/11 17:56
 */
@Service
public class ReplyServiceImpl implements ReplyService {

    @Autowired
    private ReplyExtendMapper replyExtendMapper;

    @Autowired
    private ReplyMapper replyMapper;

    /**
     * 功能描述: 查询某评论下的回复
     *
     * @param commentId 1
     * @param pageNum   2
     * @param pageSize  3
     * @return java.util.List<com.jicl.pojo.ReplyExtend>
     * @author xianzilei
     * @date 2019/12/11 17:59
     **/
    @Override
    public List<ReplyExtend> getReplyList(Integer commentId, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize, "reply_time");
        List<ReplyExtend> replyExtendList = replyExtendMapper.getReplys(commentId);
        return replyExtendList;
    }

    /**
     * 功能描述: 新增回复信息
     *
     * @param commentId     1
     * @param blogId        2
     * @param content       3
     * @param user          4
     * @param repliedUserId
     * @param replyType
     * @return void
     * @author xianzilei
     * @date 2019/12/11 19:48
     **/
    @Override
    public void addReplys(Integer commentId, Integer blogId, String content, User user, Integer repliedUserId,
                          String replyType) {
        Reply reply = new Reply();
        reply.setCommentId(commentId);
        reply.setReplyContent(content);
        reply.setUserId(user.getUserId());
        Date date = new Date();
        reply.setRepliedUserId(repliedUserId);
        reply.setReplyType(replyType);
        reply.setReplyTime(date);
        reply.setCreateTime(date);
        reply.setUpdateTime(date);
        reply.setDelFlag(false);
        replyMapper.insertSelective(reply);
    }
}
