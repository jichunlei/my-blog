package com.jicl.constant;

/**
 * 博客数据字典
 *
 * @author : xianzilei
 * @date : 2019/12/1 18:01
 */
public interface BlogDataDictionary {
    /*==============用户角色类型==================*/
    /**
     * 超级管理员
     */
    String USER_ROLE_SUPER_ADMIN = "1";
    /**
     * 普通管理员
     */
    String USER_ROLE_GENERAL_ADMIN = "2";
    /**
     * VIP用户
     */
    String USER_ROLE_VIP_USER = "3";
    /**
     * 普通用户
     */
    String USER_ROLE_GENERAL_USER = "4";


    /*==============用户状态==================*/
    /**
     * 正常
     */
    String USER_STATUS_NORMAL = "1";
    /**
     * 封禁
     */
    String USER_STATUS_BAN = "0";
}
