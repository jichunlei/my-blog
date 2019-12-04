package com.jicl.service;

import com.jicl.dto.LoginDto;
import com.jicl.dto.RegisterDto;
import com.jicl.entity.User;

/**
 * 用户管理服务层接口
 *
 * @author : xianzilei
 * @date : 2019/12/1 18:14
 */
public interface UserService {

    void register(RegisterDto registerDto,String remoteIp);

    User checkUser(LoginDto loginDto);

    void updateLoginInfo(User user,String lastLoginIp);
}
