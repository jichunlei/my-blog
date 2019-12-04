package com.jicl.pojo;

import com.jicl.entity.Type;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 首页类型排行榜
 *
 * @author : xianzilei
 * @date : 2019/12/1 09:55
 */
@Data
@Builder
@EqualsAndHashCode
public class TopType extends Type {
    /**
     * 类型下对应的博客数目
     */
    private Integer blogNums;
}
