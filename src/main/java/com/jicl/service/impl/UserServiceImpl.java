package com.jicl.service.impl;

import com.jicl.constant.BlogConstant;
import com.jicl.constant.BlogDataDictionary;
import com.jicl.dto.LoginDto;
import com.jicl.dto.RegisterDto;
import com.jicl.entity.User;
import com.jicl.entity.UserExample;
import com.jicl.exception.NotFoundException;
import com.jicl.mapper.UserMapper;
import com.jicl.service.UserService;
import com.jicl.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
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
     * @param remoteIp 客户机IP
     * @return void
     * @author xianzilei
     * @date 2019/12/1 18:25
     **/
    @Override
    public void register(RegisterDto registerDto,String remoteIp) {
        User user=new User();
        user.setUsername(registerDto.getUsername());//用户名
        user.setPassword(MD5Utils.code(registerDto.getPassword()));//密码
        user.setNickname(registerDto.getNickname());//昵称
        user.setEmail(registerDto.getEmail());//邮箱
        user.setTelephone(registerDto.getTelephone());//联系方式
        user.setUserRole(BlogDataDictionary.USER_ROLE_GENERAL_USER);//角色类型为正常
        user.setUserStatus(BlogDataDictionary.USER_STATUS_NORMAL);//用户状态为正常
        user.setHeadPortrait(BlogConstant.DEFAULT_HEAD_PORTRAIT);//使用默认头像
        user.setLastLoginIp(remoteIp);//最后登录的Ip
        Date now = new Date();
        user.setRegisterTime(now);//注册时间
        user.setLastLoginTime(now);//最后登录时间
        user.setCreateTime(now);//创建时间
        user.setUpdateTime(now);//更新时间
        user.setDelFlag("0");//删除标识
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
    public User checkUser(LoginDto loginDto){
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUsernameEqualTo(loginDto.getUsername()).andPasswordEqualTo(MD5Utils.code(loginDto.getPassword())).andDelFlagEqualTo("0");
        List<User> list = userMapper.selectByExample(userExample);
        if(CollectionUtils.isEmpty(list)){
            return null;
        }
        return list.get(0);
    }

    /**
     * 功能描述: 更新登录信息
     *
     * @param user 1
     * @param lastLoginIp 2
     * @return void
     * @author xianzilei
     * @date 2019/12/1 20:27
     **/
    @Override
    public void updateLoginInfo(User user,String lastLoginIp) {
        User info = new User();
        info.setUserId(user.getUserId());
        info.setLastLoginIp(lastLoginIp);
        info.setLastLoginTime(new Date());
        userMapper.updateByPrimaryKeySelective(info);
    }
}
