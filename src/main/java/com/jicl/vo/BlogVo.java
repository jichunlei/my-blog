package com.jicl.vo;

import com.jicl.entity.Blog;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 博客信息vo
 *
 * @author : xianzilei
 * @date : 2019/12/3 13:58
 */
@Data
@Builder
@EqualsAndHashCode(callSuper = false)
public class BlogVo extends Blog {
    /**
     * 博客作者昵称
     */
    private String nickname;


    /**
     * 博客作者头像
     */
    private String headPortrait;
}
