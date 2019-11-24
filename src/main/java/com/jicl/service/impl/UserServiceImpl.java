package com.jicl.service.impl;

import com.jicl.dao.UserRepository;
import com.jicl.pojo.User;
import com.jicl.service.UserService;
import com.jicl.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Auther: xianzilei
 * @Date: 2019/11/24 10:30
 * @Description: 用户管理实现类
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * 用户登录校验
     *
     * @param username 用户名
     * @param password 密码
     * @return: com.jicl.pojo.User
     * @auther: xianzilei
     * @date: 2019/11/24 10:30
     **/
    @Override
    public User checkUser(String username, String password) {
        User user = userRepository.findByUsernameAndPassword(username, MD5Utils.code(password));
        return user;
    }
}
