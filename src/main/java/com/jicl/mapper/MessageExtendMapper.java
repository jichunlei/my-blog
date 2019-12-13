package com.jicl.mapper;

import com.jicl.entity.MessageExample;
import com.jicl.pojo.MessageExtend;

import java.util.List;

/**
 * 留言扩展mapper
 *
 * @author : xianzilei
 * @date : 2019/12/12 18:54
 */
public interface MessageExtendMapper {
    /**
     * 功能描述: 分页获取留言信箱列表
     *
     * @return java.util.List<com.jicl.pojo.MessageExtend>
     * @author xianzilei
     * @date 2019/12/12 19:02
     **/
    List<MessageExtend> page(MessageExample messageExample);
}
