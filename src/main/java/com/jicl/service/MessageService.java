package com.jicl.service;

import com.github.pagehelper.PageInfo;
import com.jicl.pojo.MessageExtend;

/**
 * 留言服务层接口
 *
 * @author : xianzilei
 * @date : 2019/12/12 18:52
 */
public interface MessageService {
    /**
     * 功能描述: 分页获取留言信箱列表
     *
     * @param pageNum 1
     * @param pageSize 2
     * @return java.util.List<com.jicl.pojo.MessageExtend>
     * @author xianzilei
     * @date 2019/12/12 19:00
     **/
    PageInfo<MessageExtend> page(Integer pageNum, Integer pageSize);
}
