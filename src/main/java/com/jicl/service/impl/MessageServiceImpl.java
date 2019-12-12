package com.jicl.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jicl.mapper.MessageExtendMapper;
import com.jicl.pojo.MessageExtend;
import com.jicl.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 留言服务实现类
 *
 * @author : xianzilei
 * @date : 2019/12/12 18:52
 */
@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageExtendMapper messageExtendMapper;

    /**
     * 功能描述: 分页获取留言信箱列表
     *
     * @param pageNum  1
     * @param pageSize 2
     * @return java.util.List<com.jicl.pojo.MessageExtend>
     * @author xianzilei
     * @date 2019/12/12 19:01
     **/
    @Override
    public PageInfo<MessageExtend> page(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<MessageExtend> list = messageExtendMapper.page();
        for (MessageExtend messageExtend : list) {
            
        }
        return PageInfo.of(list);
    }
}
