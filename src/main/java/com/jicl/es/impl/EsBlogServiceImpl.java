package com.jicl.es.impl;

import com.jicl.constant.RedisConstant;
import com.jicl.entity.BlogTagExample;
import com.jicl.entity.User;
import com.jicl.es.EsBlogDo;
import com.jicl.es.EsBlogRepository;
import com.jicl.es.EsBlogService;
import com.jicl.mapper.BlogTagMapper;
import com.jicl.mapper.UserMapper;
import com.jicl.util.RedisValueUtil;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 * es服务实现
 *
 * @author : xianzilei
 * @date : 2020/1/19 16:35
 */
@Service
public class EsBlogServiceImpl implements EsBlogService {

    @Autowired
    private EsBlogRepository esBlogRepository;

    @Autowired
    private RedisValueUtil redisValueUtil;

    @Autowired
    private BlogTagMapper blogTagMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public Page<EsBlogDo> search(String keyword, Integer pageNum, Integer pageSize) {
        //参数不合法调整
        if (pageNum < 0) {
            pageNum = 0;
        }
        QueryBuilder query = null;
        if (StringUtils.isNotBlank(keyword)) {
            //组装模糊查询条件
            query = QueryBuilders.fuzzyQuery("blogTitle", keyword);
        } else {
            query = QueryBuilders.boolQuery();
        }
        Page<EsBlogDo> page = esBlogRepository.search(query, PageRequest.of(pageNum, pageSize,
                new Sort(Sort.Direction.DESC, "blogViews")));
        for (EsBlogDo esBlogDo : page) {
            BlogTagExample blogTagExample = new BlogTagExample();
            blogTagExample.createCriteria().andBlogIdEqualTo(esBlogDo.getBlogId());
            esBlogDo.setTags(blogTagMapper.selectByExample(blogTagExample));
            User user = userMapper.selectByPrimaryKey(esBlogDo.getUserId());
            esBlogDo.setNickname(user.getNickname());
            esBlogDo.setHeadPortrait(user.getHeadPortrait());
            //从缓存中取博客评论数和浏览数
            if (redisValueUtil.hExists(RedisConstant.COMMENT_KEY,
                    esBlogDo.getBlogId().toString())) {
                esBlogDo.setBlogViews((Integer) redisValueUtil.hGet(RedisConstant.VIEW_KEY,
                        esBlogDo.getBlogId().toString()));
            }
            if (redisValueUtil.hExists(RedisConstant.COMMENT_KEY,
                    esBlogDo.getBlogId().toString())) {
                esBlogDo.setBlogComments((Integer) redisValueUtil.hGet(RedisConstant.COMMENT_KEY,
                        esBlogDo.getBlogId().toString()));
            }
        }
        return page;
    }
}
