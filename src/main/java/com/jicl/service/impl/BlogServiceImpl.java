package com.jicl.service.impl;

import com.jicl.NotFoundException;
import com.jicl.dao.BlogRepository;
import com.jicl.pojo.Blog;
import com.jicl.pojo.Type;
import com.jicl.service.BlogService;
import com.jicl.util.MyBeanUtils;
import com.jicl.vo.BlogQuery;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Auther: xianzilei
 * @Date: 2019/11/24 20:36
 * @Description: 博客管理实现类
 */
@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogRepository blogRepository;

    /**
     * 根据编号查询博客信息
     *
     * @param id 1
     * @return: com.jicl.pojo.Blog
     * @auther: xianzilei
     * @date: 2019/11/24 20:24
     **/
    @Override
    public Blog getBlog(Long id) {
        return blogRepository.findOne(id);
    }

    /**
     * TODO
     *
     * @param id 1
     * @return: com.jicl.pojo.Blog
     * @auther: xianzilei
     * @date: 2019/11/24 20:24
     **/
    @Override
    public Blog getAndConvert(Long id) {
        return null;
    }

    /**
     * 根据条件分页查询博客信息
     *
     * @param pageable 1
     * @param blog     2
     * @return: org.springframework.data.domain.Page<com.jicl.pojo.Blog>
     * @auther: xianzilei
     * @date: 2019/11/24 20:24
     **/
    @Override
    public Page<Blog> listBlog(Pageable pageable, BlogQuery blog) {
        return blogRepository.findAll((root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            //博客标题
            if (StringUtils.isNotBlank(blog.getTitle())) {
                predicates.add(criteriaBuilder.like(root.get("title"),
                        "%" + blog.getTitle() +
                        "%"));
            }
            //博客类型
            if (blog.getTypeId() != null) {
                predicates.add(criteriaBuilder.equal(root.<Type>get("type").get("id"),
                        blog.getTypeId()));
            }
            //是否推荐
            if (blog.isRecommend()) {
                predicates.add(criteriaBuilder.equal(root.<Boolean>get("recommend"),
                        blog.isRecommend()));
            }
            criteriaQuery.where(predicates.toArray(new Predicate[predicates.size()]));
            return null;
        }, pageable);
    }

    /**
     * 分页查询博客信息
     *
     * @param pageable 1
     * @return: org.springframework.data.domain.Page<com.jicl.pojo.Blog>
     * @auther: xianzilei
     * @date: 2019/11/24 20:24
     **/
    @Override
    public Page<Blog> listBlog(Pageable pageable) {
        return blogRepository.findAll(pageable);
    }

    /**
     * TODO
     *
     * @param tagId    1
     * @param pageable 2
     * @return: org.springframework.data.domain.Page<com.jicl.pojo.Blog>
     * @auther: xianzilei
     * @date: 2019/11/24 20:24
     **/
    @Override
    public Page<Blog> listBlog(Long tagId, Pageable pageable) {
        return null;
    }

    /**
     * TODO
     *
     * @param query    1
     * @param pageable 2
     * @return: org.springframework.data.domain.Page<com.jicl.pojo.Blog>
     * @auther: xianzilei
     * @date: 2019/11/24 20:24
     **/
    @Override
    public Page<Blog> listBlog(String query, Pageable pageable) {
        return null;
    }

    /**
     * TODO
     *
     * @param size 1
     * @return: java.util.List<com.jicl.pojo.Blog>
     * @auther: xianzilei
     * @date: 2019/11/24 20:24
     **/
    @Override
    public List<Blog> listRecommendBlogTop(Integer size) {
        return null;
    }

    /**
     * TODO
     *
     * @return: java.util.Map<java.lang.String , java.util.List < com.jicl.pojo.Blog>>
     * @auther: xianzilei
     * @date: 2019/11/24 20:24
     **/
    @Override
    public Map<String, List<Blog>> archiveBlog() {
        return null;
    }

    /**
     * TODO
     *
     * @return: java.lang.Long
     * @auther: xianzilei
     * @date: 2019/11/24 20:24
     **/
    @Override
    public Long countBlog() {
        return null;
    }

    /**
     * 新增博客信息
     *
     * @param blog 1
     * @return: com.jicl.pojo.Blog
     * @auther: xianzilei
     * @date: 2019/11/24 20:24
     **/
    @Override
    @Transactional
    public Blog saveBlog(Blog blog) {
        Date date = new Date();
        blog.setUpdateTime(date);
        if (blog.getId() == null) {
            blog.setCreateTime(date);
            blog.setViews(0);
        }
        return blogRepository.save(blog);
    }

    /**
     * 更新博客信息
     *
     * @param id   1
     * @param blog 2
     * @return: com.jicl.pojo.Blog
     * @auther: xianzilei
     * @date: 2019/11/24 20:25
     **/
    @Override
    @Transactional
    public Blog updateBlog(Long id, Blog blog) {
        Blog b = blogRepository.findOne(id);
        if (b == null) {
            throw new NotFoundException("该博客不存在");
        }
        BeanUtils.copyProperties(blog, b, MyBeanUtils.getNullPropertyNames(blog));
        b.setUpdateTime(new Date());
        return blogRepository.save(b);
    }

    /**
     * 删除博客信息
     *
     * @param id 1
     * @return: void
     * @auther: xianzilei
     * @date: 2019/11/24 20:25
     **/
    @Override
    @Transactional
    public void deleteBlog(Long id) {
        blogRepository.delete(id);
    }
}
