package com.jicl.dto;

import lombok.Data;

/**
 * 登录提交信息传输类
 *
 * @author : xianzilei
 * @date : 2019/12/1 19:45
 */
@Data
public class LoginDto {
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
}
