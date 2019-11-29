package com.jicl.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author :xianzilei123
 * @Date :2019-11-29
 * @Description :评论实体类
 */
@Data
@Accessors(chain = true)
@TableName("t_comment")
public class Comment extends Model<Comment> {

    private static final long serialVersionUID = 1L;

    /**
     * 评论id
     */
    @TableId(value = "comment_id", type = IdType.AUTO)
    private Integer commentId;
    /**
     * 评论内容
     */
    @TableField("comment_content")
    private String commentContent;
    /**
     * 评论用户id
     */
    @TableField("user_id")
    private Integer userId;
    /**
     * 评论的博客id
     */
    @TableField("blog_id")
    private Long blogId;
    /**
     * 评论时间
     */
    @TableField("comment_time")
    private Date commentTime;
    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;
    /**
     * 删除标识
     */
    @TableField("del_flag")
    private Boolean delFlag;


    @Override
    protected Serializable pkVal() {
        return this.commentId;
    }

}