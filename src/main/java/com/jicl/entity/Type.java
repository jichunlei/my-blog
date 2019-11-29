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
 * @Description :博客类型实体类
 */
@Data
@Accessors(chain = true)
@TableName("t_type")
public class Type extends Model<Type> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "type_id", type = IdType.AUTO)
    private Integer typeId;
    /**
     * 类型名
     */
    @TableField("type_name")
    private String typeName;


    @Override
    protected Serializable pkVal() {
        return this.typeId;
    }

}