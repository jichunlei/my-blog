package com.jicl.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Author :xianzilei123
 * @Date :2019-11-29
 * @Description :博客标签关系实体类
 */
@Data
@Accessors(chain = true)
@TableName("t_blog_tag")
public class BlogTag extends Model<BlogTag> {

    private static final long serialVersionUID = 1L;

    /**
     * 博客id
     */
    @TableField("blog_id")
    private Integer blogId;
    /**
     * 标签id
     */
    @TableField("tags_id")
    private Integer tagsId;


    @Override
    protected Serializable pkVal() {
        return null;
    }

}