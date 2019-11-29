package com.jicl.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.util.Date;

/**
 * @Author :xianzilei123
 * @Date :2019-11-29
 * @Description :用户实体类
 */
@Data
@Accessors(chain = true)
@TableName("t_user")
public class User extends Model<User> {

    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    @TableId(value = "user_id", type = IdType.AUTO)
    private Long userId;
    /**
     * 用户登录名
     */
    private String username;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 登录密码
     */
    private String password;
    /**
     * 角色类型：1-超级管理员；2-普通管理员；3-VIP用户；4-普通用户
     */
    @TableField("user_role")
    private String userRole;
    /**
     * 用户状态：0-封禁；1-正常
     */
    @TableField("user_status")
    private String userStatus;
    /**
     * 联系方式
     */
    private String telephone;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 用户头像
     */
    @TableField("head_portrait")
    private String headPortrait;
    /**
     * 注册时间
     */
    @TableField("register_time")
    private Date registerTime;
    /**
     * 最后登录时间
     */
    @TableField("last_login_time")
    private Date lastLoginTime;
    /**
     * 最后登录ip
     */
    @TableField("last_login_ip")
    private String lastLoginIp;
    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;
    /**
     * 更新时间
     */
    @TableField("update_time")
    private Date updateTime;
    /**
     * 删除标识
     */
    @TableField("del_flag")
    private String delFlag;


    @Override
    protected Serializable pkVal() {
        return this.userId;
    }

}