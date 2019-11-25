package com.jicl.service.impl;

import com.jicl.NotFoundException;
import com.jicl.dao.TypeRepository;
import com.jicl.pojo.Type;
import com.jicl.service.TypeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Auther: xianzilei
 * @Date: 2019/11/24 12:59
 * @Description: TODO
 */
@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeRepository typeRepository;
    /**
     * 新增分类
     *
     * @param type 1
     * @return: com.jicl.pojo.Type
     * @auther: xianzilei
     * @date: 2019/11/24 13:00
     **/
    @Override
    @Transactional
    public Type saveType(Type type) {
        return typeRepository.save(type);
    }

    /**
     * 根据id查询分类
     *
     * @param id 1
     * @return: com.jicl.pojo.Type
     * @auther: xianzilei
     * @date: 2019/11/24 13:00
     **/
    @Override
    public Type getType(Long id) {
        return typeRepository.findOne(id);
    }

    /**
     * 根据类型名称查询分类
     *
     * @param name 1
     * @return: com.jicl.pojo.Type
     * @auther: xianzilei
     * @date: 2019/11/24 13:00
     **/
    @Override
    public Type getTypeByName(String name) {
        return typeRepository.findByName(name);
    }

    /**
     * 分页查询分类信息
     *
     * @param pageable 1
     * @return: org.springframework.data.domain.Page<com.jicl.pojo.Type>
     * @auther: xianzilei
     * @date: 2019/11/24 13:00
     **/
    @Override
    public Page<Type> listType(Pageable pageable) {
        return typeRepository.findAll(pageable);
    }

    /**
     * 查询所有的类型信息
     *
     * @return: java.util.List<com.jicl.pojo.Type>
     * @auther: xianzilei
     * @date: 2019/11/24 13:01
     **/
    @Override
    public List<Type> listType() {
        return typeRepository.findAll();
    }

    /**
     * 查询分类热点数据
     *
     * @param size 1
     * @return: java.util.List<com.jicl.pojo.Type>
     * @auther: xianzilei
     * @date: 2019/11/24 13:01
     **/
    @Override
    public List<Type> listTypeTop(Integer size) {
        Sort sort = new Sort(Sort.Direction.DESC,"blogs.size");
        Pageable pageable = new PageRequest(0,size,sort);
        return typeRepository.findTop(pageable);
    }

    /**
     * 修改分类信息
     *
     * @param id 1
     * @param type 2
     * @return: com.jicl.pojo.Type
     * @auther: xianzilei
     * @date: 2019/11/24 13:01
     **/
    @Override
    @Transactional
    public Type updateType(Long id, Type type) {
        Type t = typeRepository.findOne(id);
        if(t==null){
            throw new NotFoundException("不存在该类型信息");
        }
        BeanUtils.copyProperties(type,t);
        return typeRepository.save(t);
    }

    /**
     * 删除分类信息
     *
     * @param id 1
     * @return: void
     * @auther: xianzilei
     * @date: 2019/11/24 13:01
     **/
    @Override
    @Transactional
    public void deleteType(Long id) {
        typeRepository.delete(id);
    }
}
