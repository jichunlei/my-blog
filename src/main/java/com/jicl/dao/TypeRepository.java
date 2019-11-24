package com.jicl.dao;

import com.jicl.pojo.Type;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @Auther: xianzilei
 * @Date: 2019/11/24 13:05
 * @Description: 类型管理持久层接口
 */
public interface TypeRepository extends JpaRepository<Type,Long> {
    /**
     * 根据类型名查询类型信息
     *
     * @param name 类型名
     * @return: com.jicl.pojo.Type
     * @auther: xianzilei
     * @date: 2019/11/24 13:07
     **/
    Type findByName(String name);

    /**
     * 分页查询分类列表
     *
     * @param pageable 1
     * @return: java.util.List<com.jicl.pojo.Type>
     * @auther: xianzilei
     * @date: 2019/11/24 13:08
     **/
    @Query("select t from Type t")
    List<Type> findTop(Pageable pageable);
}
