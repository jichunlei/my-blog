package com.jicl.sercvice.impl;

import com.jicl.entity.Comment;
import com.jicl.mapper.CommentMapper;
import com.jicl.service.CommentService;
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
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

}
