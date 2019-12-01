package com.jicl.mapper;

import com.jicl.entity.Blog;
import com.jicl.pojo.TopType;

import java.util.List;

/**
 * 博客扩展mapper
 *
 * @author : xianzilei
 * @date : 2019/12/1 09:49
 */
public interface BlogExtendMapper {

    /**
     * 功能描述: 查询指定数量的top类型的博客数
     *
     * @param topSize 1
     * @return java.util.List<com.jicl.pojo.TopType>
     * @author xianzilei
     * @data 2019/12/1 10:30
     **/
    List<TopType> getTopTypeList(Integer topSize);

    /**
     * 功能描述: 查询指定数量的推荐的博客
     *
     * @param topSize 1
     * @return java.util.List<com.jicl.entity.Blog>
     * @author xianzilei
     * @date 2019/12/1 10:44
     **/
    List<Blog> getRecommendBlogs(Integer topSize);
}
