package com.jicl.sercvice.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jicl.entity.Blog;
import com.jicl.mapper.BlogMapper;
import com.jicl.sercvice.BlogService;
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
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements BlogService {

}
