package com.jicl.service;

import com.github.pagehelper.PageInfo;
import com.jicl.entity.Blog;
import java.util.List;

/**
 * 博客管理服务实现类
 *
 * @author : xianzilei
 * @date : 2019/11/30 10:29
 */
public interface BlogService {

    /**
     * 功能描述: 分页查询博客列表
     *
     * @param blog 1
     * @param pageNum 2
     * @param pageSize 3
     * @return com.github.pagehelper.PageInfo<com.jicl.entity.Blog>
     * @author xianzilei
     * @date 2019/11/30 10:16
     **/
    PageInfo<Blog> page(Blog blog, Integer pageNum, Integer pageSize);

    /**
     * 功能描述: 获取所有博客信息
     *
     * @return java.util.List<com.jicl.entity.Blog>
     * @author xianzilei
     * @date 2019/11/30 10:35
     **/
    List<Blog> getAll();
}
