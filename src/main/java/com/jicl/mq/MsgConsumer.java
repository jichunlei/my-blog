package com.jicl.mq;

import com.jicl.exception.BusinessException;
import com.jicl.pojo.MailMessageInfo;
import com.jicl.util.MailUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.TextMessage;
import javax.mail.MessagingException;
import java.util.regex.Pattern;

/**
 * 消息接收器
 *
 * @author : xianzilei
 * @date : 2020/3/31 15:25
 */
@Component
@Slf4j
public class MsgConsumer {

    @Autowired
    private MailUtil mailUtil;

    @Value("${spring.mail.username}")
    private String sender;

    /**
     * 功能描述: 处理留言消息
     *
     * @param textMessage 1
     * @return void
     * @author xianzilei
     * @date 2020/3/31 15:36
     **/
    @JmsListener(destination = "${queueName.message}")
    public void receiveMessageMsg(TextMessage textMessage) throws JMSException, BusinessException
            , MessagingException {
        //text格式为：留言者昵称-留言内容
        log.info("接收到留言消息：{}", textMessage.getText());
        String text = textMessage.getText();
        if (StringUtils.isBlank(text)) {
            throw new BusinessException("消息格式不正确");
        }
        String[] strings = text.split("-");
        if (strings.length != 2) {
            throw new BusinessException("消息格式不正确");
        }
        String name = strings[0];
        String content = strings[1];
        MailMessageInfo mailMessageInfo = new MailMessageInfo();
        mailMessageInfo.setEmail(sender);
        mailMessageInfo.setSubject("留言新增通知");
        StringBuffer sb = new StringBuffer();
        sb.append("<h2>有个小伙伴给你留言啦！</h2>")
                .append("<hr>")
                .append("<p style='text-align:left'>留言者：" + name + "</p>")
                .append("<p style='text-align:left'>留言内容：" + content + "</p>")
                .append("<hr>")
                .append("<p style='text-align:right'>此为系统邮件，请勿回复</p>")
                .append("<hr>")
                .append("<a href='http://xianzilei.cn/toMessagePage'>点此跳转博客留言板页面</a>");
        mailMessageInfo.setContent(sb.toString());
        mailUtil.sendMail(mailMessageInfo);
    }

    //处理留言回复消息
    @JmsListener(destination = "${queueName.message.reply}")
    public void receiveMessageReplyMsg(TextMessage textMessage) throws JMSException,
            BusinessException,
            MessagingException {
        //text格式为：回复者昵称-回复内容-被回复者昵称-被回复内容-邮箱
        log.info("接收到留言回复消息：{}", textMessage.getText());
        String text = textMessage.getText();
        if (StringUtils.isBlank(text)) {
            throw new BusinessException("消息格式不正确");
        }
        String[] strings = text.split("-");
        if (strings.length != 5) {
            throw new BusinessException("消息格式不正确");
        }
        String name = strings[0];
        String content = strings[1];
        String parentName = strings[2];
        String parentContent = strings[3];
        String email = strings[4];
        String check = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)" +
                "+[a-zA-Z]{2,}$";
        Pattern regex = Pattern.compile(check);
        if (!regex.matcher(email).matches()) {
            throw new BusinessException("邮箱不合法！");
        }
        MailMessageInfo mailMessageInfo = new MailMessageInfo();
        mailMessageInfo.setEmail(email);
        mailMessageInfo.setSubject("您收到一条回复（来自Blog）");
        StringBuffer sb = new StringBuffer();
        sb.append("<h1>您好，" + parentName + "！有个小伙伴回复您啦！</h1>")
                .append("<hr>")
                .append("<h2>您的留言</h2>")
                .append("<p style='text-align:left'>留言内容：" + parentContent + "</p>")
                .append("<hr>")
                .append("<h2>您收到的回复</h2>")
                .append("<p style='text-align:left'>留言者：" + name + "</p>")
                .append("<p style='text-align:left'>留言内容：" + content + "</p>")
                .append("<hr>")
                .append("<p style='text-align:right'>此为系统邮件，请勿回复</p>")
                .append("<hr>")
                .append("<a href='http://xianzilei.cn/toMessagePage'>点此跳转博客留言板页面</a>");
        mailMessageInfo.setContent(sb.toString());
        mailUtil.sendMail(mailMessageInfo);
    }
}
