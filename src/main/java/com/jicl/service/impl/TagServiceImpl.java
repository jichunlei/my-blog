package com.jicl.service.impl;

import com.jicl.NotFoundException;
import com.jicl.dao.TagRepository;
import com.jicl.pojo.Tag;
import com.jicl.service.TagService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: xianzilei
 * @Date: 2019/11/24 19:16
 * @Description: 标签管理实现类
 */
@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagRepository tagRepository;

    /**
     * 新增标签信息
     *
     * @param type 1
     * @return: com.jicl.pojo.Tag
     * @auther: xianzilei
     * @date: 2019/11/24 19:14
     **/
    @Override
    public Tag saveTag(Tag type) {
        return tagRepository.save(type);
    }

    /**
     * 根据id查询标签信息
     *
     * @param id 1
     * @return: com.jicl.pojo.Tag
     * @auther: xianzilei
     * @date: 2019/11/24 19:14
     **/
    @Override
    public Tag getTag(Long id) {
        return tagRepository.findOne(id);
    }

    /**
     * 根据标签名查询标签信息
     *
     * @param name 1
     * @return: com.jicl.pojo.Tag
     * @auther: xianzilei
     * @date: 2019/11/24 19:14
     **/
    @Override
    public Tag getTagByName(String name) {
        return tagRepository.findByName(name);
    }

    /**
     * 分页查询标签信息
     *
     * @param pageable 1
     * @return: org.springframework.data.domain.Page<com.jicl.pojo.Tag>
     * @auther: xianzilei
     * @date: 2019/11/24 19:14
     **/
    @Override
    public Page<Tag> listTag(Pageable pageable) {
        return tagRepository.findAll(pageable);
    }

    /**
     * 查询所有的标签信息
     *
     * @return: java.util.List<com.jicl.pojo.Tag>
     * @auther: xianzilei
     * @date: 2019/11/24 19:14
     **/
    @Override
    public List<Tag> listTag() {
        return tagRepository.findAll();
    }

    /**
     * 取指定数量的top数据
     *
     * @param size 1
     * @return: java.util.List<com.jicl.pojo.Tag>
     * @auther: xianzilei
     * @date: 2019/11/24 19:14
     **/
    @Override
    public List<Tag> listTagTop(Integer size) {
        Sort sort = new Sort(Sort.Direction.DESC, "blogs.size");
        Pageable pageable = new PageRequest(0, size, sort);
        return tagRepository.findTop(pageable);
    }

    /**
     * 批量查询标签信息
     *
     * @param ids 1
     * @return: java.util.List<com.jicl.pojo.Tag>
     * @auther: xianzilei
     * @date: 2019/11/24 19:14
     **/
    @Override
    public List<Tag> listTag(String ids) {
        return tagRepository.findAll(convertToList(ids));
    }

    /**
     * 更新标签信息
     *
     * @param id 1
     * @param tag 2
     * @return: com.jicl.pojo.Tag
     * @auther: xianzilei
     * @date: 2019/11/24 19:14
     **/
    @Override
    public Tag updateTag(Long id, Tag tag) {
        Tag t = tagRepository.findOne(id);
        if (t == null) {
            throw new NotFoundException("不存在该标签");
        }
        BeanUtils.copyProperties(tag,t);
        return tagRepository.save(t);
    }

    /**
     * 删除标签信息
     *
     * @param id 1
     * @return: void
     * @auther: xianzilei
     * @date: 2019/11/24 19:14
     **/
    @Override
    public void deleteTag(Long id) {
        tagRepository.delete(id);
    }

    //字符串转Long类型的list
    private List<Long> convertToList(String ids) {
        List<Long> list = new ArrayList<>();
        if (StringUtils.isNotBlank(ids)) {
            String[] idarray = ids.split(",");
            for (int i=0; i < idarray.length;i++) {
                list.add(new Long(idarray[i]));
            }
        }
        return list;
    }
}
