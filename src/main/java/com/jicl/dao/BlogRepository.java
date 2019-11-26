package com.jicl.dao;

import com.jicl.pojo.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Auther: xianzilei
 * @Date: 2019/11/24 20:37
 * @Description: 博客管理持久层接口
 */
public interface BlogRepository extends JpaRepository<Blog, Long>, JpaSpecificationExecutor<Blog> {
    /**
     * TODO
     *
     * @param pageable 1
     * @return: java.util.List<com.jicl.pojo.Blog>
     * @auther: xianzilei
     * @date: 2019/11/24 20:38
     **/
    @Query("select b from Blog b where b.recommend = true")
    List<Blog> findTop(Pageable pageable);

    /**
     * 根据关键字查询博客信息
     *
     * @param query    1
     * @param pageable 2
     * @return: org.springframework.data.domain.Page<com.jicl.pojo.Blog>
     * @auther: xianzilei
     * @date: 2019/11/24 20:38
     **/
    @Query("select b from Blog b where b.title like ?1 or b.content like ?1")
    Page<Blog> findByQuery(String query, Pageable pageable);

    /**
     * TODO
     *
     * @param id 1
     * @return: int
     * @auther: xianzilei
     * @date: 2019/11/24 20:38
     **/
    @Transactional
    @Modifying
    @Query("update Blog b set b.views = b.views+1 where b.id = ?1")
    int updateViews(Long id);

    /**
     * TODO
     *
     * @return: java.util.List<java.lang.String>
     * @auther: xianzilei
     * @date: 2019/11/24 20:38
     **/
    @Query("select function('date_format',b.updateTime,'%Y') as year from Blog b group by " +
            "function('date_format',b.updateTime,'%Y') order by year desc ")
    List<String> findGroupYear();

    /**
     * TODO
     *
     * @param year 1
     * @return: java.util.List<com.jicl.pojo.Blog>
     * @auther: xianzilei
     * @date: 2019/11/24 20:38
     **/
    @Query("select b from Blog b where function('date_format',b.updateTime,'%Y') = ?1")
    List<Blog> findByYear(String year);
}
