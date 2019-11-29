package com.jicl.sercvice.impl;

import com.jicl.entity.User;
import com.jicl.mapper.UserMapper;
import com.jicl.service.UserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xianzilei123
 * @since 2019-11-29
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
