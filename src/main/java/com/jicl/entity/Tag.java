package com.jicl.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Author :xianzilei123
 * @Date :2019-11-29
 * @Description :标签实体类
 */
@Data
@Accessors(chain = true)
@TableName("t_tag")
public class Tag extends Model<Tag> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "tag_id", type = IdType.AUTO)
    private Integer tagId;
    /**
     * 标签名
     */
    @TableField("tag_name")
    private String tagName;


    @Override
    protected Serializable pkVal() {
        return this.tagId;
    }

}