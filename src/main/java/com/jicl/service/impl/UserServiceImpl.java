package com.jicl.service.impl;

import com.jicl.constant.BlogConstant;
import com.jicl.constant.BlogDataDictionary;
import com.jicl.dto.LoginDto;
import com.jicl.dto.RegisterDto;
import com.jicl.entity.User;
import com.jicl.entity.UserExample;
import com.jicl.mapper.UserMapper;
import com.jicl.service.UserService;
import com.jicl.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;

/**
 * 用户管理服务层实现
 *
 * @author : xianzilei
 * @date : 2019/12/1 18:17
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 功能描述: 用户注册
     *
     * @param registerDto 注册提交的信息
     * @param remoteIp    客户机IP
     * @return void
     * @author xianzilei
     * @date 2019/12/1 18:25
     **/
    @Override
    public void register(RegisterDto registerDto, String remoteIp) {
        Date now = new Date();
        User user = User.builder().username(registerDto.getUsername())
                .password(MD5Utils.code(registerDto.getPassword()))
                .nickname(registerDto.getNickname())
                .userGender(registerDto.getGender())
                .email(registerDto.getEmail())
                .telephone(registerDto.getTelephone())
                .userRole(BlogDataDictionary.USER_ROLE_GENERAL_USER)
                .userStatus(BlogDataDictionary.USER_STATUS_NORMAL)
                .headPortrait(registerDto.getGender() ? BlogConstant.DEFAULT_HEAD_MALE_PORTRAIT :
                        BlogConstant.DEFAULT_HEAD_FEMALE_PORTRAIT)
                .lastLoginIp(remoteIp)
                .registerTime(now)
                .lastLoginTime(now)
                .createTime(now)
                .updateTime(now).delFlag(false)
                .build();
        userMapper.insertSelective(user);
    }

    /**
     * 功能描述: 登录校验
     *
     * @param loginDto 1
     * @author xianzilei
     * @date 2019/12/1 19:47
     **/
    @Override
    public User checkUser(LoginDto loginDto) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUsernameEqualTo(loginDto.getUsername()).andPasswordEqualTo(MD5Utils.code(loginDto.getPassword())).andDelFlagEqualTo(false);
        List<User> list = userMapper.selectByExample(userExample);
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        return list.get(0);
    }

    /**
     * 功能描述: 更新登录信息
     *
     * @param user        1
     * @param lastLoginIp 2
     * @return void
     * @author xianzilei
     * @date 2019/12/1 20:27
     **/
    @Override
    public void updateLoginInfo(User user, String lastLoginIp) {
        User record =
                User.builder().userId(user.getUserId()).lastLoginIp(lastLoginIp).lastLoginTime(new Date()).build();
        userMapper.updateByPrimaryKeySelective(record);
    }
}
