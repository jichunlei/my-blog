package com.jicl.service;

import com.github.pagehelper.PageInfo;
import com.jicl.entity.Tag;
import com.jicl.entity.TagExample;

import java.util.List;
import java.util.Map;

/**
 * 标签服务层接口
 *
 * @author : xianzilei
 * @date : 2019/12/2 18:58
 */
public interface TagService {

    /**
     * 功能描述: 查询所有标签（map形式）
     *
     * @return java.util.Map<java.lang.Integer, java.lang.String>
     * @author xianzilei
     * @date 2019/12/18 15:07
     **/
    Map<Integer, String> getAllTags();

    /**
     * 功能描述: 查询所有标签列表（map形式）
     *
     * @return java.util.List<com.jicl.entity.Tag>
     * @author xianzilei
     * @date 2019/12/18 15:08
     **/
    List<Tag> getAll();

    /**
     * 功能描述: 分页查询标签信息
     *
     * @param tagExample 1
     * @param pageNum    2
     * @param pageSize   3
     * @return com.github.pagehelper.PageInfo<com.jicl.entity.Tag>
     * @author xianzilei
     * @date 2019/12/24 9:42
     **/
    PageInfo<Tag> page(TagExample tagExample, Integer pageNum, Integer pageSize);

    /**
     * 功能描述: 主键查询
     *
     * @param id 1
     * @return com.jicl.entity.Type
     * @author xianzilei
     * @date 2019/12/24 10:03
     **/
    Tag findOne(Integer id);

    /**
     * 功能描述: 根据名称查询标签信息
     *
     * @param tagName 1
     * @return com.jicl.entity.Type
     * @author xianzilei
     * @date 2019/12/24 12:14
     **/
    Tag findTagByName(String tagName);

    /**
     * 功能描述: 保存标签信息
     *
     * @param tag 1
     * @return void
     * @author xianzilei
     * @date 2019/12/24 12:15
     **/
    void saveTag(Tag tag);

    /**
     * 功能描述: 删除标签信息
     *
     * @param id 1
     * @return void
     * @author xianzilei
     * @date 2019/12/24 13:48
     **/
    void deleteTag(Integer id);
}
