package com.jicl.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jicl.constant.RedisConstant;
import com.jicl.entity.Blog;
import com.jicl.entity.BlogExample;
import com.jicl.entity.BlogTag;
import com.jicl.entity.BlogTagExample;
import com.jicl.es.EsBlogDo;
import com.jicl.es.EsBlogRepository;
import com.jicl.mapper.BlogExtendMapper;
import com.jicl.mapper.BlogMapper;
import com.jicl.mapper.BlogTagExtendMapper;
import com.jicl.mapper.BlogTagMapper;
import com.jicl.pojo.TopTag;
import com.jicl.pojo.TopType;
import com.jicl.service.BlogService;
import com.jicl.util.MarkdownUtils;
import com.jicl.util.RedisValueUtil;
import com.jicl.vo.BlogVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.*;

/**
 * 博客管理服务实现类
 *
 * @author : xianzilei
 * @date : 2019/11/30 10:29
 **/
@Slf4j
@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogMapper blogMapper;

    @Autowired
    private BlogExtendMapper blogExtendMapper;

    @Autowired
    private BlogTagExtendMapper blogTagExtendMapper;

    @Autowired
    private BlogTagMapper blogTagMapper;

    @Autowired
    private EsBlogRepository esBlogRepository;

    @Autowired
    private RedisValueUtil redisValueUtil;

    /**
     * 功能描述: 分页查询博客信息
     *
     * @param blogExample 1
     * @param pageNum     2
     * @param pageSize    3
     * @param userId      4
     * @return com.github.pagehelper.PageInfo<com.jicl.vo.BlogVo>
     * @author xianzilei
     * @date 2020/2/1 21:13
     **/
    @Override
    public PageInfo<BlogVo> page(BlogExample blogExample, Integer pageNum,
                                 Integer pageSize, Integer userId) {
        PageHelper.startPage(pageNum, pageSize);
        List<BlogVo> list = blogExtendMapper.page(blogExample);
        for (BlogVo blogVo : list) {
            BlogTagExample blogTagExample = new BlogTagExample();
            blogTagExample.createCriteria().andBlogIdEqualTo(blogVo.getBlogId());
            blogVo.setTags(blogTagMapper.selectByExample(blogTagExample));
            //从缓存中取博客评论数和浏览数
            if (redisValueUtil.hExists(RedisConstant.COMMENT_KEY,
                    blogVo.getBlogId().toString())) {
                Integer blogComments = (Integer) redisValueUtil.hGet(RedisConstant.COMMENT_KEY,
                        blogVo.getBlogId().toString());
                blogVo.setBlogComments(blogComments);
                log.info("更新博客编号[{}]的评论数为redis最新评论数据：{}", blogVo.getBlogId(),
                        blogComments);
            }
            if (redisValueUtil.hExists(RedisConstant.VIEW_KEY,
                    blogVo.getBlogId().toString())) {
                Integer blogViews = (Integer) redisValueUtil.hGet(RedisConstant.VIEW_KEY,
                        blogVo.getBlogId().toString());
                blogVo.setBlogViews(blogViews);
                log.info("更新博客编号[{}]的浏览数为redis最新浏览数据：{}", blogVo.getBlogId(),
                        blogViews);
            }
            if (redisValueUtil.hExists(RedisConstant.LIKE_KEY,
                    blogVo.getBlogId().toString())) {
                HashSet<Integer> set =
                        (HashSet<Integer>) redisValueUtil.hGet(RedisConstant.LIKE_KEY,
                                blogVo.getBlogId().toString());
                blogVo.setBlogLikes(set.size());
                if (userId == null) {
                    blogVo.setFlag(false);
                } else {
                    blogVo.setFlag(set.contains(userId));
                }
                log.info("更新博客编号[{}]的点赞数为redis最新点赞数据：{}", blogVo.getBlogId(),
                        set.size());
            }
        }
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

    /**
     * 功能描述: 获取博客详情
     *
     * @param blogId 1
     * @return com.jicl.vo.BlogVo
     * @author xianzilei
     * @date 2019/12/9 17:53
     **/
    @Override
    @Transactional(rollbackFor = Exception.class)
    public BlogVo viewBlogDetail(Integer blogId, Integer userId) {
        //查询博客详情
        BlogVo blogVo = blogExtendMapper.getBlogDeatil(blogId);
        //将markdown内容转换成html
        blogVo.setBlogContent(MarkdownUtils.markdownToHtmlExtensions(blogVo.getBlogContent()));
        //查询博客的标签列表
        BlogTagExample blogTagExample = new BlogTagExample();
        blogTagExample.createCriteria().andBlogIdEqualTo(blogVo.getBlogId());
        blogVo.setTags(blogTagMapper.selectByExample(blogTagExample));
        blogVo.setBlogComments((Integer) redisValueUtil.hGet(RedisConstant.COMMENT_KEY,
                blogId.toString()));
        blogVo.setBlogViews((Integer) redisValueUtil.hGet(RedisConstant.VIEW_KEY,
                blogId.toString()));
        HashSet<Integer> set =
                (HashSet<Integer>) redisValueUtil.hGet(RedisConstant.LIKE_KEY,
                        blogVo.getBlogId().toString());
        blogVo.setBlogLikes(set.size());
        if (userId == null) {
            blogVo.setFlag(false);
        } else {
            blogVo.setFlag(set.contains(userId));
        }
        redisValueUtil.hIncr(RedisConstant.VIEW_KEY, blogId.toString());
        return blogVo;
    }

    /**
     * 功能描述: 单条查询博客基本信息
     *
     * @param blogId 1
     * @return com.jicl.entity.Blog
     * @author xianzilei
     * @date 2019/12/9 21:47
     **/
    @Override
    public Blog findOne(Integer blogId) {
        return blogMapper.selectByPrimaryKey(blogId);
    }

    /**
     * 功能描述: 更新博客的评论数
     *
     * @param blogId 1
     * @author xianzilei
     * @date 2019/12/11 16:26
     **/
    @Override
    public void updateBlogComments(Integer blogId) {
        redisValueUtil.hIncr(RedisConstant.COMMENT_KEY, blogId.toString());
    }

    /**
     * 功能描述: 博客归档
     *
     * @param userId 1
     * @return java.util.Map<java.lang.String                                                                                                                                                                                                                                                               ,                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               java.util.List                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               <                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               com.jicl.entity.Blog>>
     * @author xianzilei
     * @date 2019/12/19 17:59
     **/
    @Override
    public Map<String, List<Blog>> archiveBlog(Integer userId) {
        List<String> years = blogExtendMapper.findGroupYear(userId);
        Map<String, List<Blog>> map = new HashMap<>(years.size());
        for (String year : years) {
            map.put(year, blogExtendMapper.findByYear(year, userId));
        }
        return map;
    }

    /**
     * 功能描述: 查询博客总数
     *
     * @return java.lang.Long
     * @author xianzilei
     * @date 2019/12/12 13:57
     **/
    @Override
    public Long countBlog(Integer userId) {
        BlogExample blogExample = new BlogExample();
        BlogExample.Criteria criteria = blogExample.createCriteria().andDelFlagEqualTo(false);
        if (userId != null) {
            criteria.andUserIdEqualTo(userId);
        }
        return blogMapper.countByExample(blogExample);
    }

    /**
     * 功能描述: 新增博客
     *
     * @param blog 1
     * @author xianzilei
     * @date 2019/12/18 19:13
     **/
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addBlog(Blog blog) {
        //新增博客
        if (blog.getRecommend() == null) {
            blog.setRecommend(false);
        }
        if (blog.getAppreciationFlag() == null) {
            blog.setAppreciationFlag(false);
        }
        if (blog.getCommentabled() == null) {
            blog.setCommentabled(false);
        }
        if (blog.getShareFlag() == null) {
            blog.setShareFlag(false);
        }
        if (StringUtils.isBlank(blog.getBlogFlag())) {
            blog.setBlogFlag("原创");
        }
        String tagIdStr = blog.getTagIdStr();
        blog.setBlogViews(0);
        blog.setTagIdStr("," + tagIdStr + ",");
        blog.setBlogComments(0);
        blog.setBlogLikes(0);
        Date date = new Date();
        blog.setCreateTime(date);
        blog.setUpdateTime(date);
        blog.setDelFlag(false);
        blogMapper.insertSelective(blog);
        //新增博客标签对应关系
        String[] tagIds = tagIdStr.split(",");
        updateBlogTag(blog.getBlogId(), tagIds, blog.getPublished());
        log.info("redis新增博客编号[{}]的基本信息>>>>>>start",blog.getBlogId());
        redisValueUtil.hPut(RedisConstant.COMMENT_KEY, blog.getBlogId().toString(), 0);
        redisValueUtil.hPut(RedisConstant.VIEW_KEY, blog.getBlogId().toString(), 0);
        redisValueUtil.hPut(RedisConstant.LIKE_KEY, blog.getBlogId().toString(), new HashSet<Integer>());
        log.info("redis新增博客编号[{}]的基本信息>>>>>>end",blog.getBlogId());
    }

    /**
     * 功能描述: 更新博客
     *
     * @param blog 1
     * @author xianzilei
     * @date 2019/12/18 19:13
     **/
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateBlog(Blog blog) {
        //更新博客
        if (blog.getRecommend() == null) {
            blog.setRecommend(false);
        }
        if (blog.getShareFlag() == null) {
            blog.setShareFlag(false);
        }
        if (blog.getAppreciationFlag() == null) {
            blog.setAppreciationFlag(false);
        }
        if (blog.getCommentabled() == null) {
            blog.setCommentabled(false);
        }
        if (!blog.getTagIdStr().startsWith(",")) {
            blog.setTagIdStr("," + blog.getTagIdStr());
        }
        String tagIdStr = blog.getTagIdStr();
        String[] tagIds = tagIdStr.substring(1).split(",");
        blog.setTagIdStr(tagIdStr + ",");
        Date date = new Date();
        blog.setUpdateTime(date);
        blog.setDelFlag(false);
        blogMapper.updateByPrimaryKeySelective(blog);
        //更新博客标签对应关系
        updateBlogTag(blog.getBlogId(), tagIds, blog.getPublished());
    }

    /**
     * 功能描述: 更新博客标签对应关系
     *
     * @param blogId    1
     * @param tagIds    2
     * @param published 3
     * @author xianzilei
     * @date 2020/1/19 10:51
     **/
    private void updateBlogTag(Integer blogId, String[] tagIds, Boolean published) {
        BlogTagExample blogTagExample = new BlogTagExample();
        blogTagExample.createCriteria().andBlogIdEqualTo(blogId);
        blogTagMapper.deleteByExample(blogTagExample);
        //只有发布才将标签信息写入关系表中
        if (published) {
            BlogTag blogTag = new BlogTag();
            blogTag.setBlogId(blogId);
            for (String tagId : tagIds) {
                blogTag.setTagId(Integer.parseInt(tagId));
                blogTagMapper.insertSelective(blogTag);
            }
        }
    }

    /**
     * 功能描述: 驳回博客
     *
     * @param id 1
     * @return void
     * @author xianzilei
     * @date 2019/12/23 16:30
     **/
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void rejectBlog(Integer id) {
        Blog blog = new Blog();
        blog.setBlogId(id);
        blog.setPublished(false);
        blog.setUpdateTime(new Date());
        blogMapper.updateByPrimaryKeySelective(blog);
        BlogTagExample blogTagExample = new BlogTagExample();
        blogTagExample.createCriteria().andBlogIdEqualTo(id);
        blogTagMapper.deleteByExample(blogTagExample);
    }

    /**
     * 功能描述: 删除博客
     *
     * @param id 1
     * @author xianzilei
     * @date 2019/12/19 8:39
     **/
    @Override
    public void deleteBlog(Integer id) {
        Blog blog = new Blog();
        Date date = new Date();
        blog.setBlogId(id);
        blog.setDelFlag(true);
        blog.setDelTime(date);
        blog.setUpdateTime(date);
        blogMapper.updateByPrimaryKeySelective(blog);
    }

    /**
     * 功能描述: 同步博客信息至es中
     *
     * @return java.lang.Integer
     * @author xianzilei
     * @date 2020/1/7 11:40
     **/
    @Override
    public Integer syncBlogToEs() {
        Calendar beforeTime = Calendar.getInstance();
        beforeTime.add(Calendar.MINUTE, -10);
        Date beforeD = beforeTime.getTime();
        BlogExample blogExample = new BlogExample();
        blogExample.createCriteria().andUpdateTimeGreaterThanOrEqualTo(beforeD);
        List<Blog> list = blogMapper.selectByExampleWithBLOBs(blogExample);
        for (Blog blog : list) {
            EsBlogDo temp = new EsBlogDo();
            BeanUtils.copyProperties(blog, temp);
            esBlogRepository.save(temp);
        }
        return list.size();
    }

    /**
     * 功能描述: 同步博客评论数和浏览数
     *
     * @return java.lang.Integer
     * @author xianzilei
     * @date 2020/1/19 12:10
     **/
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void syncBlogInfoFromRedis() {
        Map<String, Serializable> commentsMap = redisValueUtil.hGetAll(RedisConstant.COMMENT_KEY);
        Map<String, Serializable> viewsMap = redisValueUtil.hGetAll(RedisConstant.VIEW_KEY);
        Map<String, Serializable> likesMap = redisValueUtil.hGetAll(RedisConstant.LIKE_KEY);
        //循环处理评论信息（推荐使用entrySet循环，性能较优）
        for (Map.Entry<String, Serializable> entry : commentsMap.entrySet()) {
            Integer blogId = Integer.parseInt(entry.getKey());
            Integer blogComments = (Integer) entry.getValue();
            Blog blog = blogExtendMapper.getBlogWithoutContent(blogId);
            if (!blog.getBlogComments().equals(blogComments)) {
                Integer tempComments = blog.getBlogComments();
                blog.setUpdateTime(new Date());
                blog.setBlogComments(blogComments);
                blogMapper.updateByPrimaryKeySelective(blog);
                log.info("更新博客编号[{}]的评论数完成，原值为：{}，更新后值为：{}", blogId, tempComments, blogComments);
            } else {
                log.info("博客编号[{}]的评论数未变化，无需更新，值为：{}", blogId, blogComments);
            }
        }
        //循环处理浏览量信息（推荐使用entrySet循环，性能较优）
        for (Map.Entry<String, Serializable> entry : viewsMap.entrySet()) {
            Integer blogId = Integer.parseInt(entry.getKey());
            Integer blogViews = (Integer) entry.getValue();
            Blog blog = blogExtendMapper.getBlogWithoutContent(blogId);
            if (!blog.getBlogViews().equals(blogViews)) {
                Integer tempViews = blog.getBlogViews();
                blog.setUpdateTime(new Date());
                blog.setBlogViews(blogViews);
                blogMapper.updateByPrimaryKeySelective(blog);
                log.info("更新博客编号[{}]的浏览数完成，原值为：{}，更新后值为：{}", blogId, tempViews, blogViews);
            } else {
                log.info("博客编号[{}]的浏览数未变化，无需更新，值为：{}", blogId, blogViews);
            }
        }
        //循环处理点赞数信息（推荐使用entrySet循环，性能较优）
        for (Map.Entry<String, Serializable> entry : likesMap.entrySet()) {
            Integer blogId = Integer.parseInt(entry.getKey());
            HashSet<Integer> blogLikesSet = (HashSet<Integer>) entry.getValue();
            Integer blogLikes = blogLikesSet.size();
            Blog blog = blogExtendMapper.getBlogWithoutContent(blogId);
            if (!blog.getBlogLikes().equals(blogLikes)) {
                Integer tempLikes = blog.getBlogViews();
                blog.setUpdateTime(new Date());
                blog.setBlogLikes(blogLikes);
                blogMapper.updateByPrimaryKeySelective(blog);
                log.info("更新博客编号[{}]的点赞数完成，原值为：{}，更新后值为：{}", blogId, tempLikes, blogLikes);
            } else {
                log.info("博客编号[{}]的点赞数未变化，无需更新，值为：{}", blogId, blogLikes);
            }
        }
    }

    /**
     * 功能描述: 点赞
     *
     * @param blogId 1
     * @param userId 2
     * @return void
     * @author xianzilei
     * @date 2020/2/1 20:14
     **/
    @Override
    public void addThumbsUp(Integer blogId, Integer userId) {
        HashSet<Integer> set = (HashSet<Integer>) redisValueUtil.hGet(RedisConstant.LIKE_KEY,
                blogId.toString());
        set.add(userId);
        redisValueUtil.hPut(RedisConstant.LIKE_KEY, blogId.toString(), set);
        log.info("博客编号[{}]新增点赞用户编号[{}]", blogId, userId);
    }

    /**
     * 功能描述: 取消点赞
     *
     * @param blogId 1
     * @param userId 2
     * @return void
     * @author xianzilei
     * @date 2020/2/1 20:14
     **/
    @Override
    public void cancelThumbsUp(Integer blogId, Integer userId) {
        HashSet<Integer> set = (HashSet<Integer>) redisValueUtil.hGet(RedisConstant.LIKE_KEY,
                blogId.toString());
        set.remove(userId);
        redisValueUtil.hPut(RedisConstant.LIKE_KEY, blogId.toString(), set);
        log.info("博客编号[{}]删除点赞用户编号[{}]", blogId, userId);
    }
}
