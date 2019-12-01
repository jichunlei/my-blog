package com.jicl.pojo;

import lombok.Data;

/**
 * 首页标签排行榜
 *
 * @author : xianzilei
 * @date : 2019/12/1 12:01
 */
@Data
public class TopTag {
    /**
     * 标签id
     */
    private Integer tagId;
    /**
     * 标签名
     */
    private String tagName;
    /**
     * 标签下对应的博客数目
     */
    private Integer blogNums;
}
