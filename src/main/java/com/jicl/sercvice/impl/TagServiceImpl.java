package com.jicl.sercvice.impl;

import com.jicl.entity.Tag;
import com.jicl.mapper.TagMapper;
import com.jicl.service.TagService;
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
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {

}
