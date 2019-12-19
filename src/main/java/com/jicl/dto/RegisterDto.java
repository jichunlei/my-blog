package com.jicl.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 注册功能数据传输类
 *
 * @author : xianzilei
 * @date : 2019/12/1 17:25
 */
@Data
public class RegisterDto implements Serializable {
    /**
     * 用户登录名
     */
    @NotNull
    private String username;

    /**
     * 昵称
     */
    @NotNull
    private String nickname;

    /**
     * 性别
     */
    @NotNull
    private Boolean gender;

    /**
     *  登录密码
     */
    @NotNull
    private String password;

    /**
     * 联系方式
     */
    private String telephone;

    /**
     * 邮箱
     */
    private String email;

    private static final long serialVersionUID = 1L;
}
