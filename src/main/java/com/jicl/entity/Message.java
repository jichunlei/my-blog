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
 * @Description :留言实体类
 */
@Data
@Accessors(chain = true)
@TableName("t_message")
public class Message extends Model<Message> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "message_id", type = IdType.AUTO)
    private Long messageId;
    /**
     * 游客保存为IP地址，用户保存为用户编号
     */
    @TableField("message_name")
    private String messageName;
    /**
     * 留言内容
     */
    @TableField("message_content")
    private String messageContent;
    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;


    @Override
    protected Serializable pkVal() {
        return this.messageId;
    }

}