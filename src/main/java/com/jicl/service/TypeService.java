package com.jicl.service;

import com.jicl.entity.Type;

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
}
