package com.jicl.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 消息传输类
 *
 * @author : xianzilei
 * @date : 2020/3/31 19:30
 */
@Data
public class MessageDto implements Serializable {
    private String name;
    private String content;
    private String parentName;
    private String parentContent;
    private String email;
}
