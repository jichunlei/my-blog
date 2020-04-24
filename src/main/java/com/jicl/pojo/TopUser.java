package com.jicl.pojo;

import lombok.Data;

/**
 * 首页用户排行榜
 *
 * @author : xianzilei
 * @date : 2020/4/24 15:45
 */
@Data
public class TopUser {
    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 用户昵称
     */
    private String nickname;

    /**
     * 用户头像blogNums
     */
    private String headPortrait;

    /**
     * 发表博客数
     */
    private Integer blogNums;

}
