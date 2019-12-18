package com.jicl.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jicl.entity.Blog;
import com.jicl.entity.BlogExample;
import com.jicl.entity.BlogTag;
import com.jicl.entity.BlogTagExample;
import com.jicl.exception.NotFoundException;
import com.jicl.mapper.BlogExtendMapper;
import com.jicl.mapper.BlogMapper;
import com.jicl.mapper.BlogTagExtendMapper;
import com.jicl.mapper.BlogTagMapper;
import com.jicl.pojo.TopTag;
import com.jicl.pojo.TopType;
import com.jicl.service.BlogService;
import com.jicl.util.MarkdownUtils;
import com.jicl.vo.BlogVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Autowired
    private BlogTagMapper blogTagMapper;

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
        for (BlogVo blogVo : list) {
            BlogTagExample blogTagExample = new BlogTagExample();
            blogTagExample.createCriteria().andBlogIdEqualTo(blogVo.getBlogId());
            blogVo.setTags(blogTagMapper.selectByExample(blogTagExample));
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
    public BlogVo getBlogDetail(Integer blogId) {
        //查询博客详情
        BlogVo blogVo = blogExtendMapper.getBlogDeatil(blogId);
        //将markdown内容转换成html
        blogVo.setBlogContent(MarkdownUtils.markdownToHtmlExtensions(blogVo.getBlogContent()));
        //查询博客的标签列表
        BlogTagExample blogTagExample = new BlogTagExample();
        blogTagExample.createCriteria().andBlogIdEqualTo(blogVo.getBlogId());
        blogVo.setTags(blogTagMapper.selectByExample(blogTagExample));
        UpdateBlogViews(blogId, blogVo.getBlogViews() + 1);
        return blogVo;
    }

    /**
     * 功能描述: 更新博客访问量
     *
     * @param blogId          1
     * @param updateBlogViews 2
     * @return void
     * @author xianzilei
     * @date 2019/12/10 8:41
     **/
    private void UpdateBlogViews(Integer blogId, Integer updateBlogViews) {
        Blog blog = new Blog();
        blog.setBlogViews(updateBlogViews);
        blog.setBlogId(blogId);
        blogMapper.updateByPrimaryKeySelective(blog);
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
     * 功能描述: 根据博客的评论数
     *
     * @param blogId 1
     * @return void
     * @author xianzilei
     * @date 2019/12/11 16:26
     **/
    @Override
    public void updateBlogComments(Integer blogId) {
        Blog blog = blogMapper.selectByPrimaryKey(blogId);
        if (blog == null) {
            throw new NotFoundException("博客信息不存在！");
        }
        blog.setBlogId(blogId);
        blog.setBlogComments(blog.getBlogComments() + 1);
        blogMapper.updateByPrimaryKeySelective(blog);
    }

    /**
     * 功能描述: 博客归档
     *
     * @return java.util.Map<java.lang.String, java.util.List < com.jicl.entity.Blog>>
     * @author xianzilei
     * @date 2019/12/12 13:55
     **/
    @Override
    public Map<String, List<Blog>> archiveBlog() {
        List<String> years = blogExtendMapper.findGroupYear();
        Map<String, List<Blog>> map = new HashMap<>(years.size());
        for (String year : years) {
            map.put(year, blogExtendMapper.findByYear(year));
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
    public Long countBlog() {
        return blogMapper.countByExample(null);
    }

    /**
     * 功能描述: 新增博客
     *
     * @param blog 1
     * @return void
     * @author xianzilei
     * @date 2019/12/18 19:13
     **/
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addBlog(Blog blog) {
        //新增博客
        String tagIdStr = blog.getTagIdStr();
        blog.setBlogViews(0);
        blog.setTagIdStr("," + tagIdStr);
        blog.setBlogComments(0);
        Date date = new Date();
        blog.setCreateTime(date);
        blog.setUpdateTime(date);
        blog.setDelFlag(false);
        int id = blogMapper.insertSelective(blog);
        //新增博客标签对应关系
        String[] tagIds = tagIdStr.split(",");
        updateBlogTag(id, tagIds);
    }

    /**
     * 功能描述: 更新博客
     *
     * @param blog 1
     * @return void
     * @author xianzilei
     * @date 2019/12/18 19:13
     **/
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateBlog(Blog blog) {
        //更新博客
        String tagIdStr = blog.getTagIdStr();
        String[] tagIds = tagIdStr.substring(1).split(",");
        blog.setBlogViews(0);
        blog.setBlogComments(0);
        Date date = new Date();
        blog.setUpdateTime(date);
        blog.setDelFlag(false);
        blogMapper.updateByPrimaryKeySelective(blog);
        //更新博客标签对应关系
        updateBlogTag(blog.getBlogId(), tagIds);
    }

    /**
     * 功能描述: 更新博客标签对应关系
     *
     * @param blogId 1
     * @param tagIds 2
     * @return void
     * @author xianzilei
     * @date 2019/12/18 19:48
     **/
    private void updateBlogTag(Integer blogId, String[] tagIds) {
        BlogTagExample blogTagExample = new BlogTagExample();
        blogTagExample.createCriteria().andBlogIdEqualTo(blogId);
        blogTagMapper.deleteByExample(blogTagExample);
        BlogTag blogTag = new BlogTag();
        blogTag.setBlogId(blogId);
        for (String tagId : tagIds) {
            blogTag.setTagId(Integer.parseInt(tagId));
            blogTagMapper.insertSelective(blogTag);
        }
    }
}
