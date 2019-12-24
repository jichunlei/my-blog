package com.jicl.service;

import com.github.pagehelper.PageInfo;
import com.jicl.entity.Type;
import com.jicl.entity.TypeExample;

import java.util.List;
import java.util.Map;

/**
 * 博客类型服务层接口
 *
 * @author : xianzilei
 * @date : 2019/12/2 18:48
 */
public interface TypeService {
    /**
     * 功能描述: 返回所有类型信息（Map形式）
     *
     * @return java.util.Map<java.lang.Integer,java.lang.String>
     * @author xianzilei
     * @date 2019/12/18 9:35
     **/
    Map<Integer,String> getAllTypes();

    /**
     * 功能描述: 获取所有类型信息
     *
     * @return java.util.List<com.jicl.entity.Type>
     * @author xianzilei
     * @date 2019/12/18 9:36
     **/
    List<Type> getAll();

    /**
     * 功能描述: 分页查询博客类型信息
     *
     * @param typeExample 1
     * @param pageNum 2
     * @param pageSize 3
     * @return com.github.pagehelper.PageInfo<com.jicl.entity.Type>
     * @author xianzilei
     * @date 2019/12/24 9:37
     **/
    PageInfo<Type> page(TypeExample typeExample, Integer pageNum, Integer pageSize);

    /**
     * 功能描述: 主键查询
     *
     * @param id 1
     * @return com.jicl.entity.Type
     * @author xianzilei
     * @date 2019/12/24 10:03
     **/
    Type findOne(Integer id);

    /**
     * 功能描述: 根据名称查询博客类型信息
     *
     * @param typeName 1
     * @return com.jicl.entity.Type
     * @author xianzilei
     * @date 2019/12/24 12:14
     **/
    Type findTypeByName(String typeName);

    /**
     * 功能描述: 保存类型信息
     *
     * @param type 1
     * @return void
     * @author xianzilei
     * @date 2019/12/24 12:15
     **/
    void saveType(Type type);

    /**
     * 功能描述: 删除类型信息
     *
     * @param id 1
     * @return void
     * @author xianzilei
     * @date 2019/12/24 13:48
     **/
    void deleteType(Integer id);
}
