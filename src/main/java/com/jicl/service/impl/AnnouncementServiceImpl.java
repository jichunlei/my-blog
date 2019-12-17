package com.jicl.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jicl.entity.Announcement;
import com.jicl.entity.AnnouncementExample;
import com.jicl.mapper.AnnouncementMapper;
import com.jicl.service.AnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 公告管理服务层实现
 *
 * @author : xianzilei
 * @date : 2019/12/17 19:49
 */
@Service
public class AnnouncementServiceImpl implements AnnouncementService {
    @Autowired
    private AnnouncementMapper announcementMapper;

    /**
     * 功能描述: 分页查询公告信息
     *
     * @param pageNum  1
     * @param pageSize 2
     * @return com.github.pagehelper.PageInfo<com.jicl.entity.Announcement>
     * @author xianzilei
     * @date 2019/12/17 19:54
     **/
    @Override
    public PageInfo<Announcement> page(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize, "update_time desc");
        AnnouncementExample announcementExample = new AnnouncementExample();
        announcementExample.createCriteria().andDelFlagEqualTo(false);
        List<Announcement> list = announcementMapper.selectByExample(announcementExample);
        return PageInfo.of(list);
    }
}
