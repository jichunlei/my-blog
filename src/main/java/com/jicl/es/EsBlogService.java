package com.jicl.es;


import org.springframework.data.domain.Page;

/**
 * 搜索服务接口
 *
 * @author : xianzilei
 * @date : 2020/1/19 16:34
 */
public interface EsBlogService {

    /**
     * 功能描述: 标题关键字搜索
     *
     * @param keyword 1
     * @param pageNum 2
     * @param pageSize 3
     * @return com.github.pagehelper.Page<com.jicl.es.EsBlogDo>
     * @author xianzilei
     * @date 2020/1/20 8:41
     **/
    Page<EsBlogDo> search(String keyword, Integer pageNum, Integer pageSize);
}
