package com.jicl.dao;

import com.jicl.pojo.Comment;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Auther: xianzilei
 * @Date: 2019/11/27 08:37
 * @Description: 评论管理持久层接口
 */
public interface CommentRepository extends JpaRepository<Comment,Long>{

    /**
     * 功能描述: TODO
     *
     * @param blogId 1
     * @param sort 2
     * @return: java.util.List<com.jicl.pojo.Comment>
     * @auther: xianzilei
     * @date: 2019/11/27 9:05
     **/
    List<Comment> findByBlogIdAndParentCommentNull(Long blogId, Sort sort);
}
