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
 * @Description :博客实体类
 */
@Data
@Accessors(chain = true)
@TableName("t_blog")
public class Blog extends Model<Blog> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "blog_id", type = IdType.AUTO)
    private Integer blogId;
    /**
     * 博主id，对应用户id
     */
    @TableField("user_id")
    private Integer userId;
    /**
     * 博客描述
     */
    @TableField("blog_desc")
    private String blogDesc;
    /**
     * 是否推荐
     */
    private Boolean recommend;
    /**
     * 转载声明是否开启
     */
    @TableField("share_flag")
    private Boolean shareFlag;
    /**
     * 博客类型
     */
    @TableField("type_id")
    private Long typeId;
    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;
    /**
     * 更新时间
     */
    @TableField("update_time")
    private Date updateTime;
    /**
     * 首图地址
     */
    @TableField("first_picture_addr")
    private String firstPictureAddr;
    private Boolean published;
    /**
     * 博客标题
     */
    @TableField("blog_title")
    private String blogTitle;
    /**
     * 博客内容
     */
    @TableField("blog_content")
    private String blogContent;
    /**
     * 博客浏览次数
     */
    @TableField("blog_views")
    private Integer blogViews;
    /**
     * 是否开启赞赏
     */
    @TableField("appreciation_flag")
    private Boolean appreciationFlag;
    /**
     * 是否开启评论
     */
    private Boolean commentabled;
    /**
     * 博客评论次数
     */
    @TableField("blog_comments")
    private Integer blogComments;


    @Override
    protected Serializable pkVal() {
        return this.blogId;
    }

}