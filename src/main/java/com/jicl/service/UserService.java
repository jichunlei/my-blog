package com.jicl.service;

import com.jicl.pojo.User;

/**
 * @Auther: xianzilei
 * @Date: 2019/11/24 10:26
 * @Description: 用户服务接口类
 */
public interface UserService {

    /**
     * 用户登录校验
     *
     * @param username 1
     * @param password 2
     * @return: com.jicl.pojo.User
     * @auther: xianzilei
     * @date: 2019/11/24 10:27
     **/
    User checkUser(String username, String password);
}
