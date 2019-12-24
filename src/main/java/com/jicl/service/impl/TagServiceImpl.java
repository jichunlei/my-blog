package com.jicl.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jicl.entity.Tag;
import com.jicl.entity.TagExample;
import com.jicl.mapper.TagMapper;
import com.jicl.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Date;
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

    /**
     * 功能描述: 查询所有标签列表（map形式）
     *
     * @return java.util.List<com.jicl.entity.Tag>
     * @author xianzilei
     * @date 2019/12/18 15:08
     **/
    @Override
    public List<Tag> getAll() {
        TagExample tagExample = new TagExample();
        tagExample.createCriteria().andDelFlagEqualTo(false);
        return tagMapper.selectByExample(tagExample);
    }

    /**
     * 功能描述: 分页查询标签信息
     *
     * @param tagExample 1
     * @param pageNum    2
     * @param pageSize   3
     * @return com.github.pagehelper.PageInfo<com.jicl.entity.Tag>
     * @author xianzilei
     * @date 2019/12/24 9:43
     **/
    @Override
    public PageInfo<Tag> page(TagExample tagExample, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Tag> list = tagMapper.selectByExample(tagExample);
        return PageInfo.of(list);
    }

    /**
     * 功能描述: 主键查询
     *
     * @param id 1
     * @return com.jicl.entity.Tag
     * @author xianzilei
     * @date 2019/12/24 13:58
     **/
    @Override
    public Tag findOne(Integer id) {
        return tagMapper.selectByPrimaryKey(id);
    }

    /**
     * 功能描述: 根据名称查询标签信息
     *
     * @param tagName 1
     * @return com.jicl.entity.Tag
     * @author xianzilei
     * @date 2019/12/24 13:58
     **/
    @Override
    public Tag findTagByName(String tagName) {
        TagExample tagExample = new TagExample();
        tagExample.createCriteria().andTagNameEqualTo(tagName);
        List<Tag> list = tagMapper.selectByExample(tagExample);
        return CollectionUtils.isEmpty(list) ? null : list.get(0);
    }

    /**
     * 功能描述: 保存标签信息
     *
     * @param tag 1
     * @return void
     * @author xianzilei
     * @date 2019/12/24 13:58
     **/
    @Override
    public void saveTag(Tag tag) {
        Date date = new Date();
        tag.setUpdateTime(date);
        if (tag.getTagId() == null) {
            tag.setCreateTime(date);
            tag.setDelFlag(false);
            tagMapper.insertSelective(tag);
        } else {
            tagMapper.updateByPrimaryKeySelective(tag);
        }
    }

    /**
     * 功能描述: 删除标签信息
     *
     * @param id 1
     * @return void
     * @author xianzilei
     * @date 2019/12/24 13:58
     **/
    @Override
    public void deleteTag(Integer id) {
        Tag tag = new Tag();
        tag.setTagId(id);
        tag.setDelFlag(true);
        tag.setDelTime(new Date());
        tagMapper.updateByPrimaryKeySelective(tag);
    }
}
