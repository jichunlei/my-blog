package com.jicl.service;

import com.github.pagehelper.PageInfo;
import com.jicl.entity.User;
import com.jicl.pojo.MessageExtend;

/**
 * 留言服务层接口
 *
 * @author : xianzilei
 * @date : 2019/12/12 18:52
 */
public interface MessageService {
    /**
     * 功能描述: 分页获取留言信箱列表
     *
     * @param pageNum 1
     * @param pageSize 2
     * @return java.util.List<com.jicl.pojo.MessageExtend>
     * @author xianzilei
     * @date 2019/12/12 19:00
     **/
    PageInfo<MessageExtend> page(Integer pageNum, Integer pageSize);

    /**
     * 功能描述: 新增留言
     *
     * @param nickname 1
     * @param email 2
     * @param content 3
     * @param user 4
     * @return void
     * @author xianzilei
     * @date 2019/12/17 15:39
     **/
    void addMessage(String nickname, String email, String content, User user);

    /**
     * 功能描述: 新增留言的回复
     *
     * @param nickname 1
     * @param email 2
     * @param content 3
     * @param messageId 4
     * @param repliedUserId 5
     * @param repliedUserNickname 6
     * @param user 7
     * @return void
     * @author xianzilei
     * @date 2019/12/17 18:40
     **/
    void addMessageReply(String nickname, String email, String content, Integer messageId, Integer repliedUserId, String repliedUserNickname, User user);
}
