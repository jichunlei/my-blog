package com.jicl.sercvice.impl;

import com.jicl.entity.Message;
import com.jicl.mapper.MessageMapper;
import com.jicl.service.MessageService;
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
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements MessageService {

}
