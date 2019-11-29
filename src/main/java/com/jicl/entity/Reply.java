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
 * @Description :回复实体类
 */
@Data
@Accessors(chain = true)
@TableName("t_reply")
public class Reply extends Model<Reply> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "reply_id", type = IdType.AUTO)
    private Integer replyId;
    /**
     * 回复内容
     */
    @TableField("reply_content")
    private String replyContent;
    /**
     * 回复的用户id
     */
    @TableField("user_id")
    private Integer userId;
    /**
     * 回复时间
     */
    @TableField("reply_time")
    private Date replyTime;
    /**
     * 被回复的用户id
     */
    @TableField("replied_user_id")
    private Integer repliedUserId;


    @Override
    protected Serializable pkVal() {
        return this.replyId;
    }

}