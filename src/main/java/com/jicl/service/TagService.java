package com.jicl.service;

import com.jicl.entity.Tag;

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
     * @return java.util.Map<java.lang.Integer,java.lang.String>
     * @author xianzilei
     * @date 2019/12/18 15:07
     **/
    Map<Integer,String> getAllTags();

    /**
     * 功能描述: 查询所有标签列表（map形式）
     *
     * @return java.util.List<com.jicl.entity.Tag>
     * @author xianzilei
     * @date 2019/12/18 15:08
     **/
    List<Tag> getAll();
}
