package com.jicl.service;

import com.jicl.pojo.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @Auther: xianzilei
 * @Date: 2019/11/24 12:57
 * @Description: 分类管理接口
 */
public interface TypeService {
    /**
     * 新增分类
     *
     * @param type 1
     * @return: com.jicl.pojo.Type
     * @auther: xianzilei
     * @date: 2019/11/24 13:00
     **/
    Type saveType(Type type);

    /**
     * 根据id查询分类
     *
     * @param id 1
     * @return: com.jicl.pojo.Type
     * @auther: xianzilei
     * @date: 2019/11/24 13:00
     **/
    Type getType(Long id);

    /**
     * 根据类型名称查询分类
     *
     * @param name 1
     * @return: com.jicl.pojo.Type
     * @auther: xianzilei
     * @date: 2019/11/24 13:00
     **/
    Type getTypeByName(String name);

    /**
     * 分页查询分类信息
     *
     * @param pageable 1
     * @return: org.springframework.data.domain.Page<com.jicl.pojo.Type>
     * @auther: xianzilei
     * @date: 2019/11/24 13:00
     **/
    Page<Type> listType(Pageable pageable);

    /**
     * TODO
     *
     * @param  1
     * @return: java.util.List<com.jicl.pojo.Type>
     * @auther: xianzilei
     * @date: 2019/11/24 13:01
     **/
    List<Type> listType();

    /**
     * TODO
     *
     * @param size 1
     * @return: java.util.List<com.jicl.pojo.Type>
     * @auther: xianzilei
     * @date: 2019/11/24 13:01
     **/
    List<Type> listTypeTop(Integer size);

    /**
     * 修改分类信息
     *
     * @param id 1
     * @param type 2
     * @return: com.jicl.pojo.Type
     * @auther: xianzilei
     * @date: 2019/11/24 13:01
     **/
    Type updateType(Long id,Type type);

    /**
     * 删除分类信息
     *
     * @param id 1
     * @return: void
     * @auther: xianzilei
     * @date: 2019/11/24 13:01
     **/
    void deleteType(Long id);
}
