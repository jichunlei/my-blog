package com.jicl.dao;

import com.jicl.pojo.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @Auther: xianzilei
 * @Date: 2019/11/24 19:08
 * @Description: 标签管理持久层接口类
 */
public interface TagRepository extends JpaRepository<Tag,Long> {
    /**
     * 根据标签名查询标签信息
     *
     * @param name 1
     * @return: com.jicl.pojo.Tag
     * @auther: xianzilei
     * @date: 2019/11/24 19:08
     **/
    Tag findByName(String name);

    @Query("select t from Tag t")
    List<Tag> findTop(Pageable pageable);
}
