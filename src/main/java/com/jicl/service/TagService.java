package com.jicl.service;

import com.jicl.pojo.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @Auther: xianzilei
 * @Date: 2019/11/24 19:12
 * @Description: 标签管理接口
 */
public interface TagService {
    /**
     * 新增标签信息
     *
     * @param type 1
     * @return: com.jicl.pojo.Tag
     * @auther: xianzilei
     * @date: 2019/11/24 19:14
     **/
    Tag saveTag(Tag type);

    /**
     * 根据id查询标签信息
     *
     * @param id 1
     * @return: com.jicl.pojo.Tag
     * @auther: xianzilei
     * @date: 2019/11/24 19:14
     **/
    Tag getTag(Long id);

    /**
     * 根据标签名查询标签信息
     *
     * @param name 1
     * @return: com.jicl.pojo.Tag
     * @auther: xianzilei
     * @date: 2019/11/24 19:14
     **/
    Tag getTagByName(String name);

    /**
     * 分页查询标签信息
     *
     * @param pageable 1
     * @return: org.springframework.data.domain.Page<com.jicl.pojo.Tag>
     * @auther: xianzilei
     * @date: 2019/11/24 19:14
     **/
    Page<Tag> listTag(Pageable pageable);

    /**
     * 查询所有的标签信息
     *
     * @return: java.util.List<com.jicl.pojo.Tag>
     * @auther: xianzilei
     * @date: 2019/11/24 19:14
     **/
    List<Tag> listTag();

    /**
     * 取指定数量的top数据
     *
     * @param size 1
     * @return: java.util.List<com.jicl.pojo.Tag>
     * @auther: xianzilei
     * @date: 2019/11/24 19:14
     **/
    List<Tag> listTagTop(Integer size);

    /**
     * 批量查询标签信息
     *
     * @param ids 1
     * @return: java.util.List<com.jicl.pojo.Tag>
     * @auther: xianzilei
     * @date: 2019/11/24 19:14
     **/
    List<Tag> listTag(String ids);

    /**
     * 更新标签信息
     *
     * @param id 1
     * @param tag 2
     * @return: com.jicl.pojo.Tag
     * @auther: xianzilei
     * @date: 2019/11/24 19:14
     **/
    Tag updateTag(Long id, Tag tag);

    /**
     * 删除标签信息
     *
     * @param id 1
     * @return: void
     * @auther: xianzilei
     * @date: 2019/11/24 19:14
     **/
    void deleteTag(Long id);
}
