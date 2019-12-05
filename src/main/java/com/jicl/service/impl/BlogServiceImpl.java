package com.jicl.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jicl.entity.Blog;
import com.jicl.entity.BlogExample;
import com.jicl.mapper.BlogExtendMapper;
import com.jicl.mapper.BlogMapper;
import com.jicl.mapper.BlogTagExtendMapper;
import com.jicl.pojo.TopTag;
import com.jicl.pojo.TopType;
import com.jicl.service.BlogService;
import com.jicl.vo.BlogVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 博客管理服务实现类
 *
 * @author : xianzilei
 * @date : 2019/11/30 10:29
 **/
@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogMapper blogMapper;

    @Autowired
    private BlogExtendMapper blogExtendMapper;

    @Autowired
    private BlogTagExtendMapper blogTagExtendMapper;

    /**
     * 功能描述: 分页查询博客信息
     *
     * @param blogExample 1
     * @param pageNum     2
     * @param pageSize    3
     * @return com.github.pagehelper.PageInfo<com.jicl.vo.BlogVo>
     * @author xianzilei
     * @date 2019/12/4 22:10
     **/
    @Override
    public PageInfo<BlogVo> page(BlogExample blogExample, Integer pageNum,
                                 Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<BlogVo> list = blogExtendMapper.page(blogExample);
        return PageInfo.of(list);
    }

    /**
     * 功能描述: 查询所有博客信息
     *
     * @return java.util.List<com.jicl.entity.Blog>
     * @author xianzilei
     * @date 2019/11/30 11:25
     **/
    @Override
    public List<Blog> getAll() {
        return blogMapper.selectByExample(new BlogExample());
    }

    /**
     * 功能描述: 查询指定数量的top类型的博客数
     *
     * @param topSize 1
     * @return java.util.List<com.jicl.pojo.TopType>
     * @author xianzilei
     * @date 2019/12/1 10:32
     **/
    @Override
    public List<TopType> getTopTypeList(Integer topSize) {
        return blogExtendMapper.getTopTypeList(topSize);
    }

    /**
     * 功能描述:  查询指定数量的推荐的博客（根据博客浏览数）
     *
     * @param topSize 1
     * @return java.util.List<com.jicl.entity.Blog>
     * @author xianzilei
     * @date 2019/12/1 10:41
     **/
    @Override
    public List<Blog> getRecommendBlogs(Integer topSize) {
        return blogExtendMapper.getRecommendBlogs(topSize);
    }

    /**
     * 功能描述: 查询指定数量的top标签的博客数
     *
     * @param topSize 1
     * @return java.util.List<com.jicl.pojo.TopTag>
     * @author xianzilei
     * @date 2019/12/1 12:05
     **/
    @Override
    public List<TopTag> getTopTagList(Integer topSize) {
        return blogTagExtendMapper.getTopTagList(topSize);
    }

    /**
     * 功能描述: 获取指定数量的最新博客基本信息
     *
     * @param topSize 1
     * @return java.util.List<com.jicl.entity.Blog>
     * @author xianzilei
     * @date 2019/12/2 21:29
     **/
    @Override
    public List<Blog> getLastUpdateBlogTop(Integer topSize) {
        return blogExtendMapper.getLastUpdateBlogTop(topSize);
    }
}
