package com.jicl.pojo;

import com.jicl.entity.Tag;
import com.jicl.entity.Type;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 首页标签排行榜
 *
 * @author : xianzilei
 * @date : 2019/12/1 12:01
 */
@Data
@Builder
@EqualsAndHashCode
public class TopTag extends Tag {
    /**
     * 标签下对应的博客数目
     */
    private Integer blogNums;
}
