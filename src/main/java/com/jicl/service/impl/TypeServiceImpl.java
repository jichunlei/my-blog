package com.jicl.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jicl.entity.Type;
import com.jicl.entity.TypeExample;
import com.jicl.mapper.TypeMapper;
import com.jicl.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * 博客类型管理服务实现类
 *
 * @author : xianzilei
 * @date : 2019/12/2 18:49
 */
@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeMapper typeMapper;

    /**
     * 功能描述: 获取所有博客类型的key和value
     *
     * @return java.util.List<com.jicl.entity.Type>
     * @author xianzilei
     * @date 2019/12/2 18:50
     **/
    @Override
    public Map<Integer, String> getAllTypes() {
        TypeExample typeExample = new TypeExample();
        typeExample.createCriteria().andDelFlagEqualTo(false);
        List<Type> types = typeMapper.selectByExample(typeExample);
        return types.stream().collect(Collectors.toMap(Type::getTypeId, Type::getTypeName,
                (key1, key2) -> key2));
    }

    /**
     * 功能描述: 返回所有类型信息列表
     *
     * @return java.util.List<com.jicl.entity.Type>
     * @author xianzilei
     * @date 2019/12/18 9:37
     **/
    @Override
    public List<Type> getAll() {
        TypeExample typeExample = new TypeExample();
        typeExample.createCriteria().andDelFlagEqualTo(false);
        return typeMapper.selectByExample(typeExample);
    }

    /**
     * 功能描述: 分页查询博客类型信息
     *
     * @param typeExample 1
     * @param pageNum     2
     * @param pageSize    3
     * @return com.github.pagehelper.PageInfo<com.jicl.entity.Type>
     * @author xianzilei
     * @date 2019/12/24 9:38
     **/
    @Override
    public PageInfo<Type> page(TypeExample typeExample, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Type> list = typeMapper.selectByExample(typeExample);
        return PageInfo.of(list);
    }

    /**
     * 功能描述: 主键
     *
     * @param id 1
     * @return com.jicl.entity.Type
     * @author xianzilei
     * @date 2019/12/24 10:03
     **/
    @Override
    public Type findOne(Integer id) {
        return typeMapper.selectByPrimaryKey(id);
    }

    /**
     * 功能描述: 根据名称查询博客标签信息
     *
     * @param typeName 1
     * @return com.jicl.entity.Type
     * @author xianzilei
     * @date 2019/12/24 12:17
     **/
    @Override
    public Type findTypeByName(String typeName) {
        TypeExample typeExample = new TypeExample();
        typeExample.createCriteria().andTypeNameEqualTo(typeName);
        List<Type> list = typeMapper.selectByExample(typeExample);
        return CollectionUtils.isEmpty(list) ? null : list.get(0);
    }

    /**
     * 功能描述: 保存分类信息
     *
     * @param type 1
     * @return void
     * @author xianzilei
     * @date 2019/12/24 12:26
     **/
    @Override
    public void saveType(Type type) {
        Date date = new Date();
        type.setUpdateTime(date);
        if (type.getTypeId() == null) {
            type.setCreateTime(date);
            type.setDelFlag(false);
            typeMapper.insertSelective(type);
        } else {
            typeMapper.updateByPrimaryKeySelective(type);
        }
    }

    /**
     * 功能描述: 删除类型信息
     *
     * @param id 1
     * @return void
     * @author xianzilei
     * @date 2019/12/24 13:48
     **/
    @Override
    public void deleteType(Integer id) {
        Type type = new Type();
        type.setTypeId(id);
        type.setDelFlag(true);
        type.setDelTime(new Date());
        typeMapper.updateByPrimaryKeySelective(type);
    }
}
