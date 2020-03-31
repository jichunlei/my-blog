package com.jicl.pojo;

import lombok.Data;

import java.util.Map;

/**
 * 邮件消息类
 *
 * @author : xianzilei
 * @date : 2020/3/31 13:37
 */
@Data
public class MailMessageInfo {
    // 接收方邮件
    private String Email;
    // 主题
    private String subject;
    // 邮件内容
    private String content;
    // 模板
    private String template;
}
