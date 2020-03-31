package com.jicl.util;

import com.jicl.pojo.MailMessageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * 邮件工具类
 *
 * @author : xianzilei
 * @date : 2020/3/31 13:35
 */
@Component
@Slf4j
public class MailUtil {
    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sender;

    public void sendMail(MailMessageInfo mailMessageInfo) throws MessagingException {
        //邮件接收方
        String email = mailMessageInfo.getEmail();
        //邮件内容
        String content = mailMessageInfo.getContent();
        //邮件主题
        String subject = mailMessageInfo.getSubject();
        //组装参数
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom(sender);
        helper.setTo(email);
        helper.setSubject(subject);
//        StringBuffer sb = new StringBuffer();
//        sb.append("<h2>有个小伙伴给你留言啦！</h2>")
//                .append("<hr>")
//                .append("<p style='color:#F00'>您好，" + name + "！</p>")
//                .append("<hr>")
//                .append("<p style='text-align:left'>留言者：" + name + "</p>")
//                .append("<p style='text-align:left'>留言内容：" + content + "</p>")
//                .append("<hr>")
//                .append("<a href='http://xianzilei.cn/toMessagePage'>点此跳转博客留言板页面</a>");
        helper.setText(content, true);
        //发送邮件
        log.info("发送邮件信息到邮箱[{}]>>>>>>start", email);
        long start = System.currentTimeMillis();
        javaMailSender.send(message);
        long end = System.currentTimeMillis();
        log.info("发送邮件信息到邮箱[{}]>>>>>>end，耗时{}s", email, ((float) (end - start)) / 1000);
    }
}
