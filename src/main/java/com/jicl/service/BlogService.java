package com.jicl.service;

import com.github.pagehelper.PageInfo;
import com.jicl.entity.Blog;
import com.jicl.entity.BlogExample;
import com.jicl.pojo.TopTag;
import com.jicl.pojo.TopType;
import com.jicl.pojo.TopUser;
import com.jicl.vo.BlogVo;

import java.util.List;
import java.util.Map;

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
     * @param pageNum 2
     * @param pageSize 3
     * @param userId 4
     * @return com.github.pagehelper.PageInfo<com.jicl.vo.BlogVo>
     * @author xianzilei
     * @date 2020/2/1 21:12
     **/
    PageInfo<BlogVo> page(BlogExample blogExample, Integer pageNum, Integer pageSize,
                          Integer userId);

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
    BlogVo viewBlogDetail(Integer blogId,Integer userId);

    /**
     * 功能描述: 单条查询博客基本信息
     *
     * @param blogId 1
     * @return java.lang.Object
     * @author xianzilei
     * @date 2019/12/9 21:46
     **/
    Blog findOne(Integer blogId);

    /**
     * 功能描述: 根据博客的评论数
     *
     * @param blogId 1
     * @return void
     * @author xianzilei
     * @date 2019/12/11 16:25
     **/
    void updateBlogComments(Integer blogId);

    /**
     * 功能描述: 博客归档
     *
     * @param userId 1
     * @return java.util.Map<java.lang.String,java.util.List<com.jicl.entity.Blog>>
     * @author xianzilei
     * @date 2019/12/19 18:00
     **/
    Map<String, List<Blog>> archiveBlog(Integer userId);

    /**
     * 功能描述: 查询博客总数
     *
     * @param userId 1
     * @return java.lang.Long
     * @author xianzilei
     * @date 2019/12/19 19:08
     **/
    Long countBlog(Integer userId);

    /**
     * 功能描述: 新增博客
     *
     * @param blog 1
     * @return void
     * @author xianzilei
     * @date 2019/12/18 19:12
     **/
    void addBlog(Blog blog);

    /**
     * 功能描述: 更新博客信息
     *
     * @param blog 1
     * @return void
     * @author xianzilei
     * @date 2019/12/18 19:13
     **/
    void updateBlog(Blog blog);

    /**
     * 功能描述: 删除博客
     *
     * @param id 1
     * @return void
     * @author xianzilei
     * @date 2019/12/19 8:38
     **/
    void deleteBlog(Integer id);

    /**
     * 功能描述: 驳回博客
     *
     * @param id 1
     * @return void
     * @author xianzilei
     * @date 2019/12/23 16:29
     **/
    void rejectBlog(Integer id);

    /**
     * 功能描述: 同步博客信息至es中
     *
     * @return java.lang.Integer
     * @author xianzilei
     * @date 2020/1/7 11:40
     **/
    Integer syncBlogToEs();

    /**
     * 功能描述: 同步博客评论数和浏览数
     *
     * @author xianzilei
     * @date 2020/1/19 12:09
     **/
    void syncBlogInfoFromRedis();

    /**
     * 功能描述: 点赞
     *
     * @param blogId 1
     * @param userId 2
     * @return void
     * @author xianzilei
     * @date 2020/2/1 20:13
     **/
    void addThumbsUp(Integer blogId,Integer userId);

    /**
     * 功能描述: 取消点赞
     *
     * @param blogId 1
     * @param userId 2
     * @return void
     * @author xianzilei
     * @date 2020/2/1 20:13
     **/
    void cancelThumbsUp(Integer blogId,Integer userId);

    /**
     * 功能描述: 获取指定数量用户排行榜
     *
     * @param topSize 1
     * @return java.util.List<com.jicl.pojo.TopType>
     * @author xianzilei
     * @date 2020/4/24 15:49
     **/
    List<TopUser> getTopUserList(Integer topSize);
}
