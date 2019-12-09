package com.jicl.service;

import com.jicl.pojo.CommentExtend;

import java.util.List;

/**
 * 评论管理服务层接口
 *
 * @author : xianzilei
 * @date : 2019/12/9 18:08
 */
public interface CommentService {

    /**
     * 功能描述: 获取评论信息列表
     *
     * @param id 1
     * @param pageNum 2
     * @param blogId 3
     * @return java.util.List<com.jicl.pojo.CommentExtend>
     * @author xianzilei
     * @date 2019/12/9 18:50
     **/
    List<CommentExtend> getComments(Integer blogId, Integer pageNum, Integer pageSize);
}
