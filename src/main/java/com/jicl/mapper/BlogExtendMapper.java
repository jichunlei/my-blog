package com.jicl.mapper;

import com.jicl.entity.Blog;
import com.jicl.entity.BlogExample;
import com.jicl.pojo.TopType;
import com.jicl.vo.BlogVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 博客扩展mapper
 *
 * @author : xianzilei
 * @date : 2019/12/1 09:49
 */
public interface BlogExtendMapper {

    /**
     * 功能描述: 首页展示博客列表
     *
     * @return java.util.List<BlogVo>
     * @author xianzilei
     * @date 2019/12/3 18:56
     **/
    List<BlogVo> page(BlogExample blogExample);

    /**
     * 功能描述: 查询指定数量的top类型的博客数
     *
     * @param topSize 1
     * @return java.util.List<com.jicl.pojo.TopType>
     * @author xianzilei
     * @data 2019/12/1 10:30
     **/
    List<TopType> getTopTypeList(@Param("topSize")Integer topSize);

    /**
     * 功能描述: 查询指定数量的推荐的博客
     *
     * @param topSize 1
     * @return java.util.List<com.jicl.entity.Blog>
     * @author xianzilei
     * @date 2019/12/1 10:44
     **/
    List<Blog> getRecommendBlogs(@Param("topSize")Integer topSize);

    /**
     * 功能描述: 查询指定数量的最新的博客
     *
     * @param topSize 1
     * @return java.util.List<com.jicl.entity.Blog>
     * @author xianzilei
     * @date 2019/12/2 21:32
     **/
    List<Blog> getLastUpdateBlogTop(@Param("topSize")Integer topSize);

    /**
     * 功能描述: 博客详情查看
     *
     * @param blogId 1
     * @return com.jicl.vo.BlogVo
     * @author xianzilei
     * @date 2019/12/9 17:58
     **/
    BlogVo getBlogDeatil(@Param("blogId")Integer blogId);

    /**
     * 功能描述: 查询博客包含的年份
     *
     * @param userId 1
     * @return java.util.List<java.lang.String>
     * @author xianzilei
     * @date 2019/12/19 17:59
     **/
    List<String> findGroupYear(@Param("userId") Integer userId);

    /**
     * 功能描述: 根据年份查询博客数据
     *
     * @param year 1
     * @param userId 2
     * @return java.util.List<com.jicl.entity.Blog>
     * @author xianzilei
     * @date 2019/12/19 18:42
     **/
    List<Blog> findByYear(@Param("year") String year, @Param("userId") Integer userId);

    /**
     * 功能描述: 主键查询（不带博客正文内容）
     *
     * @param blogId 1
     * @return com.jicl.entity.Blog
     * @author xianzilei
     * @date 2020/1/19 15:09
     **/
    Blog getBlogWithoutContent(@Param("blogId")Integer blogId);
}
