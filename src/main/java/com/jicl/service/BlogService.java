package com.jicl.service;

import com.github.pagehelper.PageInfo;
import com.jicl.entity.Blog;
import com.jicl.entity.BlogExample;
import com.jicl.pojo.TopTag;
import com.jicl.pojo.TopType;
import com.jicl.vo.BlogVo;

import java.util.List;

/**
 * 博客管理服务实现类
 *
 * @author : xianzilei
 * @date : 2019/11/30 10:29
 */
public interface BlogService {

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
    PageInfo<BlogVo> page(BlogExample blogExample, Integer pageNum, Integer pageSize);

    /**
     * 功能描述: 获取所有博客信息
     *
     * @return java.util.List<com.jicl.entity.Blog>
     * @author xianzilei
     * @date 2019/11/30 10:35
     **/
    List<Blog> getAll();

    /**
     * 功能描述: 查询指定数量的top博客类型
     *
     * @param topSize 1
     * @return java.util.List<com.jicl.pojo.TopType>
     * @author xianzilei
     * @date 2019/12/1 10:32
     **/
    List<TopType> getTopTypeList(Integer topSize);

    /**
     * 功能描述: 查询指定数量的推荐的博客
     *
     * @param topSize 1
     * @return java.util.List<com.jicl.entity.Blog>
     * @author xianzilei
     * @date 2019/12/1 10:40
     **/
    List<Blog> getRecommendBlogs(Integer topSize);

    /**
     * 功能描述: 查询指定数量的top标签的博客数
     *
     * @param topSize 1
     * @return java.util.List<com.jicl.pojo.TopTag>
     * @author xianzilei
     * @date 2019/12/1 12:05
     **/
    List<TopTag> getTopTagList(Integer topSize);

    /**
     * 功能描述: 获取指定数量的最新博客基本信息
     *
     * @param topSize 1
     * @return java.util.List<com.jicl.entity.Blog>
     * @author xianzilei
     * @date 2019/12/2 21:29
     **/
    List<Blog> getLastUpdateBlogTop(Integer topSize);

    /**
     * 功能描述: 博客详情查看
     *
     * @param blogId 1
     * @return com.jicl.vo.BlogVo
     * @author xianzilei
     * @date 2019/12/9 17:53
     **/
    BlogVo getBlogDeatil(Integer blogId);
}
