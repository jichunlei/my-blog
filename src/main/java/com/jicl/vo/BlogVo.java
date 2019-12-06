package com.jicl.vo;

import com.jicl.entity.Blog;
import com.jicl.entity.BlogTag;
import lombok.Data;

import java.util.List;


/**
 * 博客信息vo
 *
 * @author : xianzilei
 * @date : 2019/12/3 13:58
 */
@Data
public class BlogVo extends Blog {
    /**
     * 博客作者昵称
     */
    private String nickname;


    /**
     * 博客作者头像
     */
    private String headPortrait;

    /**
     * 博客标签列表
     */
    private List<BlogTag> tags;
}
