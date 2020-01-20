package com.jicl.es;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * 博客搜索持久层
 *
 * @author : xianzilei
 * @date : 2019/12/27 12:00
 */
public interface EsBlogRepository extends ElasticsearchRepository<EsBlogDo, Integer> {

}
