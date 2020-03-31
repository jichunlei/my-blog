package com.jicl.mq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Queue;

/**
 * 消息发送器
 *
 * @author : xianzilei
 * @date : 2020/3/31 15:02
 */
@Slf4j
@Component
public class MsgProducer {
    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    /**
     * 功能描述: 生产消息
     *
     * @param queue   队列
     * @param message 消息
     * @return void
     * @author xianzilei
     * @date 2020/3/31 15:15
     **/
    public void prodeceMsg(Queue queue, Object message) throws JMSException {
        String queueName = queue.getQueueName();
        log.info("发送消息[{}]到队列[{}]中>>>>>>start", message, queueName);
        long start = System.currentTimeMillis();
        jmsMessagingTemplate.convertAndSend(queue, message);
        long end = System.currentTimeMillis();
        log.info("发送消息[{}]到队列[{}]中>>>>>>end，耗时{}s", message, queueName,
                ((float) (end - start)) / 1000);
    }
}
