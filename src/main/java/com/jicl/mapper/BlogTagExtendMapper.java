package com.jicl.mapper;

import com.jicl.entity.BlogTag;
import com.jicl.entity.BlogTagExample;
import com.jicl.pojo.TopTag;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BlogTagExtendMapper {

    /**
     * 功能描述: 查询指定数量的top标签的博客数
     *
     * @param topSize 1
     * @return java.util.List<com.jicl.pojo.TopTag>
     * @author xianzilei
     * @date 2019/12/1 12:05
     **/
    List<TopTag> getTopTagList(Integer topSize);
}