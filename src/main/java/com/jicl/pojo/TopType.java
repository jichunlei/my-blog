package com.jicl.pojo;

import com.jicl.entity.Type;
import lombok.Data;

/**
 * 首页类型排行榜
 *
 * @author : xianzilei
 * @date : 2019/12/1 09:55
 */
@Data
public class TopType {
    /**
     * 类型id
     */
    private Integer typeId;

    /**
     * 类型名
     */
    private String typeName;
    /**
     * 类型下对应的博客数目
     */
    private Integer blogNums;
}
