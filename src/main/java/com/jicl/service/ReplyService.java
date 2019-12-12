package com.jicl.service;

import com.jicl.entity.User;
import com.jicl.pojo.ReplyExtend;

import java.util.List;

/**
 * 回复服务接口
 *
 * @author : xianzilei
 * @date : 2019/12/11 17:55
 */
public interface ReplyService {
    /**
     * 功能描述: 查询某评论下的回复
     *
     * @param commentId
     * @param pageNum
     * @param pageSize
     * @return java.util.List<com.jicl.pojo.ReplyExtend>
     * @author xianzilei
     * @date 2019/12/11 17:58
     **/
    List<ReplyExtend> getReplyList(Integer commentId, Integer pageNum, Integer pageSize);

    /**
     * 功能描述: 新增回复信息
     *
     * @param commentId 1
     * @param blogId 2
     * @param content 3
     * @param user 4
     * @param repliedUserId
     * @param replyType
     * @return void
     * @author xianzilei
     * @date 2019/12/11 19:47
     **/
    void addReplys(Integer commentId, Integer blogId, String content, User user, Integer repliedUserId, String replyType);
}
