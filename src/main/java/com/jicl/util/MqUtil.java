package com.jicl.util;

import com.jicl.mq.MsgProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Queue;

/**
 * 消息队列工具类
 *
 * @author : xianzilei
 * @date : 2020/3/31 14:54
 */
@Slf4j
@Component
public class MqUtil {
    @Autowired
    private MsgProducer msgProducer;

    @Autowired
    @Qualifier("messageQueue")
    private Queue messageQueue;

    @Autowired
    @Qualifier("messageReplyQueue")
    private Queue messageReplyQueue;

    @Autowired
    @Qualifier("commentQueue")
    private Queue commentQueue;

    @Autowired
    @Qualifier("replyQueue")
    private Queue replyQueue;

    public void sendMessageToMq(String msg){
        try {
            msgProducer.prodeceMsg(messageQueue,msg);
        } catch (JMSException e) {
            log.error("发送留言消息[{}]异常！",msg,e);
        }
    }

    public void sendMessageReplyToMq(String msg){
        try {
            msgProducer.prodeceMsg(messageReplyQueue,msg);
        } catch (JMSException e) {
            log.error("发送留言回复消息[{}]异常！",msg,e);
        }
    }

    public void sendCommentToMq(String msg){
        try {
            msgProducer.prodeceMsg(commentQueue,msg);
        } catch (JMSException e) {
            log.error("发送评论消息[{}]异常！",msg,e);
        }
    }

    public void sendReplyToMq(String msg){
        try {
            msgProducer.prodeceMsg(replyQueue,msg);
        } catch (JMSException e) {
            log.error("发送回复消息[{}]异常！",msg,e);
        }
    }
}
