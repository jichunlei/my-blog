package com.jicl.service;

import com.jicl.pojo.Blog;
import com.jicl.vo.BlogQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

/**
 * @Auther: xianzilei
 * @Date: 2019/11/24 20:23
 * @Description: 博客管理接口类
 */
public interface BlogService {
    /**
     * 根据编号查询博客信息
     *
     * @param id 1
     * @return: com.jicl.pojo.Blog
     * @auther: xianzilei
     * @date: 2019/11/24 20:24
     **/
    Blog getBlog(Long id);

    /**
     * TODO
     *
     * @param id 1
     * @return: com.jicl.pojo.Blog
     * @auther: xianzilei
     * @date: 2019/11/24 20:24
     **/
    Blog getAndConvert(Long id);

    /**
     * 根据条件分页查询博客信息
     *
     * @param pageable 1
     * @param blog 2
     * @return: org.springframework.data.domain.Page<com.jicl.pojo.Blog>
     * @auther: xianzilei
     * @date: 2019/11/24 20:24
     **/
    Page<Blog> listBlog(Pageable pageable, BlogQuery blog);

    /**
     * 分页查询博客信息
     *
     * @param pageable 1
     * @return: org.springframework.data.domain.Page<com.jicl.pojo.Blog>
     * @auther: xianzilei
     * @date: 2019/11/24 20:24
     **/
    Page<Blog> listBlog(Pageable pageable);

    /**
     * TODO
     *
     * @param tagId 1
     * @param pageable 2
     * @return: org.springframework.data.domain.Page<com.jicl.pojo.Blog>
     * @auther: xianzilei
     * @date: 2019/11/24 20:24
     **/
    Page<Blog> listBlog(Long tagId,Pageable pageable);

    /**
     * TODO
     *
     * @param query 1
     * @param pageable 2
     * @return: org.springframework.data.domain.Page<com.jicl.pojo.Blog>
     * @auther: xianzilei
     * @date: 2019/11/24 20:24
     **/
    Page<Blog> listBlog(String query,Pageable pageable);

    /**
     * TODO
     *
     * @param size 1
     * @return: java.util.List<com.jicl.pojo.Blog>
     * @auther: xianzilei
     * @date: 2019/11/24 20:24
     **/
    List<Blog> listRecommendBlogTop(Integer size);

    /**
     * TODO
     *
     * @return: java.util.Map<java.lang.String,java.util.List<com.jicl.pojo.Blog>>
     * @auther: xianzilei
     * @date: 2019/11/24 20:24
     **/
    Map<String,List<Blog>> archiveBlog();

    /**
     * TODO
     *
     * @return: java.lang.Long
     * @auther: xianzilei
     * @date: 2019/11/24 20:24
     **/
    Long countBlog();

    /**
     * 新增博客信息
     *
     * @param blog 1
     * @return: com.jicl.pojo.Blog
     * @auther: xianzilei
     * @date: 2019/11/24 20:24
     **/
    Blog saveBlog(Blog blog);

    /**
     * 更新博客信息
     *
     * @param id 1
     * @param blog 2
     * @return: com.jicl.pojo.Blog
     * @auther: xianzilei
     * @date: 2019/11/24 20:25
     **/
    Blog updateBlog(Long id,Blog blog);

    /**
     * 删除博客信息
     *
     * @param id 1
     * @return: void
     * @auther: xianzilei
     * @date: 2019/11/24 20:25
     **/
    void deleteBlog(Long id);
}
