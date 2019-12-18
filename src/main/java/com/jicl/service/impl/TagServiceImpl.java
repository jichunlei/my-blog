package com.jicl.service.impl;

import com.jicl.entity.Tag;
import com.jicl.entity.TagExample;
import com.jicl.mapper.TagMapper;
import com.jicl.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 标签管理服务实现
 *
 * @author : xianzilei
 * @date : 2019/12/2 18:59
 */
@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagMapper tagMapper;

    /**
     * 功能描述: 获取所有的博客标签
     *
     * @return java.util.Map<java.lang.Integer, java.lang.String>
     * @author xianzilei
     * @date 2019/12/2 19:10
     **/
    @Override
    public Map<Integer, String> getAllTags() {
        TagExample tagExample = new TagExample();
        tagExample.createCriteria().andDelFlagEqualTo(false);
        List<Tag> tags = tagMapper.selectByExample(tagExample);
        return tags.stream().collect(Collectors.toMap(Tag::getTagId, Tag::getTagName, (k1, k2) -> k2));
    }

    @Override
    public List<Tag> getAll() {
        TagExample tagExample = new TagExample();
        tagExample.createCriteria().andDelFlagEqualTo(false);
        return tagMapper.selectByExample(tagExample);
    }
}
