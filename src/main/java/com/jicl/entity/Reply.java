package com.jicl.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table t_reply
 *
 * @mbg.generated do_not_delete_during_merge
 */
@Data
public class Reply implements Serializable {
    /**
     * Database Column Remarks:
     *   主键
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_reply.reply_id
     *
     * @mbg.generated
     */
    private Integer replyId;

    /**
     * Database Column Remarks:
     *   评论id
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_reply.comment_id
     *
     * @mbg.generated
     */
    private Integer commentId;

    /**
     * Database Column Remarks:
     *   回复的用户id
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_reply.user_id
     *
     * @mbg.generated
     */
    private Integer userId;

    /**
     * Database Column Remarks:
     *   回复时间
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_reply.reply_time
     *
     * @mbg.generated
     */
    private Date replyTime;

    /**
     * Database Column Remarks:
     *   被回复的用户id
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_reply.replied_user_id
     *
     * @mbg.generated
     */
    private Integer repliedUserId;

    /**
     * Database Column Remarks:
     *   回复类型：1-对评论的回复，2-对留言的回复
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_reply.reply_type
     *
     * @mbg.generated
     */
    private String replyType;

    /**
     * Database Column Remarks:
     *   创建时间
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_reply.create_time
     *
     * @mbg.generated
     */
    private Date createTime;

    /**
     * Database Column Remarks:
     *   更新时间
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_reply.update_time
     *
     * @mbg.generated
     */
    private Date updateTime;

    /**
     * Database Column Remarks:
     *   删除标志：1-删除 0-未删除
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_reply.del_flag
     *
     * @mbg.generated
     */
    private Boolean delFlag;

    /**
     * Database Column Remarks:
     *   删除时间
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_reply.del_time
     *
     * @mbg.generated
     */
    private Date delTime;

    /**
     * Database Column Remarks:
     *   回复内容
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_reply.reply_content
     *
     * @mbg.generated
     */
    private String replyContent;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_reply
     *
     * @mbg.generated
     */
    private static final long serialVersionUID = 1L;
}