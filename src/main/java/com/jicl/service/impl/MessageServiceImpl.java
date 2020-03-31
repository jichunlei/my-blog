package com.jicl.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jicl.dto.MessageDto;
import com.jicl.entity.Message;
import com.jicl.entity.MessageExample;
import com.jicl.entity.User;
import com.jicl.mapper.MessageExtendMapper;
import com.jicl.mapper.MessageMapper;
import com.jicl.mapper.ReplyExtendMapper;
import com.jicl.pojo.MessageExtend;
import com.jicl.service.MessageService;
import com.jicl.util.MqUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 留言服务实现类
 *
 * @author : xianzilei
 * @date : 2019/12/12 18:52
 */
@Slf4j
@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageExtendMapper messageExtendMapper;

    @Autowired
    private ReplyExtendMapper replyExtendMapper;

    @Autowired
    private MessageMapper messageMapper;

    @Autowired
    private MqUtil mqUtil;

    /**
     * 功能描述: 分页获取留言信箱列表
     *
     * @param pageNum  1
     * @param pageSize 2
     * @return java.util.List<com.jicl.pojo.MessageExtend>
     * @author xianzilei
     * @date 2019/12/12 19:01
     **/
    @Override
    public PageInfo<MessageExtend> page(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        MessageExample messageExample = new MessageExample();
        messageExample.createCriteria().andMessageLevelEqualTo("1").andDelFlagEqualTo(false);
        messageExample.setOrderByClause("create_time desc");
        List<MessageExtend> list = messageExtendMapper.page(messageExample);
        for (MessageExtend messageExtend : list) {
            messageExample.clear();
            messageExample.createCriteria().andMessageLevelEqualTo("2")
                    .andParentMessageIdEqualTo(messageExtend.getMessageId()).andDelFlagEqualTo(false);
            messageExample.setOrderByClause("create_time desc");
            List<Message> replyList = messageMapper.selectByExampleWithBLOBs(messageExample);
            messageExtend.setReplyList(replyList);
            messageExtend.setReplyNums(replyList.size());
        }
        return PageInfo.of(list);
    }

    /**
     * 功能描述: 新增留言
     *
     * @param nickname 1
     * @param email    2
     * @param content  3
     * @param user     4
     * @return void
     * @author xianzilei
     * @date 2019/12/17 15:39
     **/
    @Override
    public void addMessage(String nickname, String email, String content, User user) {
        Message message = new Message();
        if (user != null) {
            message.setMessageUserId(user.getUserId());
        } else {
            message.setMessageUserId(0);
        }
        message.setMessageNickname(nickname);
        message.setMessageEmail(email);
        message.setMessageContent(content);
        message.setMessageLevel("1");
        message.setParentMessageId(0);
        Date date = new Date();
        message.setCreateTime(date);
        message.setUpdateTime(date);
        message.setDelFlag(false);
        messageMapper.insertSelective(message);
        //组装数据发送到mq中，mq异步发送邮件到指定邮箱
        MessageDto messageDto = new MessageDto();
        messageDto.setName(nickname);
        messageDto.setContent(content);
        mqUtil.sendMessageToMq(messageDto);
    }

    /**
     * 功能描述: 新增留言的回复
     *
     * @param nickname            1
     * @param email               2
     * @param content             3
     * @param messageId           4
     * @param repliedUserId       5
     * @param repliedUserNickname 6
     * @param user                7
     * @return void
     * @author xianzilei
     * @date 2019/12/17 18:40
     **/
    @Override
    @Transactional
    public void addMessageReply(String nickname, String email, String content, Integer messageId,
                                Integer repliedUserId, String repliedUserNickname, User user) {
        Message message = new Message();
        if (user != null) {
            message.setMessageUserId(user.getUserId());
        } else {
            message.setMessageUserId(0);
        }
        message.setRepliedUserId(repliedUserId);
        message.setRepliedUserNickname(repliedUserNickname);
        message.setMessageNickname(nickname);
        message.setMessageEmail(email);
        message.setMessageContent(content);
        message.setMessageLevel("2");
        Date date = new Date();
        message.setParentMessageId(messageId);
        message.setCreateTime(date);
        message.setUpdateTime(date);
        message.setDelFlag(false);
        messageMapper.insertSelective(message);
        Message parentMessage = messageMapper.selectByPrimaryKey(messageId);
        if (parentMessage == null) {
            log.error("编号[{}]留言信息不存在！", messageId);
        }
        //组装数据发送到mq中，mq异步发送邮件到指定邮箱
        MessageDto messageDto = new MessageDto();
        messageDto.setName(nickname);
        messageDto.setContent(content);
        messageDto.setParentName(repliedUserNickname);
        messageDto.setParentContent(parentMessage.getMessageContent());
        messageDto.setEmail(parentMessage.getMessageEmail());
        mqUtil.sendMessageReplyToMq(messageDto);
    }

    /**
     * 功能描述: 分页查询留言信息（不考虑分层）
     *
     * @param messageExample 1
     * @param pageNum        2
     * @param pageSize       3
     * @return com.github.pagehelper.PageInfo<com.jicl.entity.Message>
     * @author xianzilei
     * @date 2019/12/24 16:11
     **/
    @Override
    public PageInfo<Message> page(MessageExample messageExample, Integer pageNum,
                                  Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Message> list = messageMapper.selectByExampleWithBLOBs(messageExample);
        return PageInfo.of(list);
    }
}
