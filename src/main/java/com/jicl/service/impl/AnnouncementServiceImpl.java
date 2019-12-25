package com.jicl.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jicl.entity.Announcement;
import com.jicl.entity.AnnouncementExample;
import com.jicl.mapper.AnnouncementMapper;
import com.jicl.service.AnnouncementService;
import com.jicl.util.MarkdownUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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
        announcementExample.createCriteria().andDelFlagEqualTo(false).andPublishedEqualTo(true);
        List<Announcement> list = announcementMapper.selectByExample(announcementExample);
        return PageInfo.of(list);
    }

    /**
     * 功能描述: 公告详情查看
     *
     * @param id 1
     * @return com.jicl.entity.Announcement
     * @author xianzilei
     * @date 2019/12/24 8:36
     **/
    @Override
    public Announcement getAnnc(Integer id) {
        Announcement announcement = announcementMapper.selectByPrimaryKey(id);
        announcement.setAnncContent(MarkdownUtils.markdownToHtmlExtensions(announcement.getAnncContent()));
        return announcement;
    }

    /**
     * 功能描述: 分页查询公告信息（自定义查询条件）
     *
     * @param announcementExample 1
     * @param pageNum             2
     * @param pageSize            3
     * @return com.github.pagehelper.PageInfo<com.jicl.entity.Announcement>
     * @author xianzilei
     * @date 2019/12/25 12:13
     **/
    @Override
    public PageInfo<Announcement> page(AnnouncementExample announcementExample, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Announcement> list = announcementMapper.selectByExample(announcementExample);
        return PageInfo.of(list);
    }

    /**
     * 功能描述: 主键查询
     *
     * @param id 1
     * @return com.jicl.entity.Announcement
     * @author xianzilei
     * @date 2019/12/25 14:00
     **/
    @Override
    public Announcement findOne(Integer id) {
        return announcementMapper.selectByPrimaryKey(id);
    }

    /**
     * 功能描述: 更新公告信息
     *
     * @param announcement 1
     * @return void
     * @author xianzilei
     * @date 2019/12/25 14:21
     **/
    @Override
    public void saveAnnouncement(Announcement announcement) {
        Date date = new Date();
        announcement.setUpdateTime(date);
        if (announcement.getTopFlag() == null) {
            announcement.setTopFlag(false);
        }
        if (announcement.getAnncId() == null) {
            announcement.setCreateTime(date);
            announcement.setDelFlag(false);
            announcementMapper.insertSelective(announcement);
        } else {
            announcementMapper.updateByPrimaryKeySelective(announcement);
        }
    }

    /**
     * 功能描述: 删除公告信息
     *
     * @param id 1
     * @return void
     * @author xianzilei
     * @date 2019/12/25 14:21
     **/
    @Override
    public void deleteAnnouncement(Integer id) {
        Announcement announcement = new Announcement();
        announcement.setAnncId(id);
        announcement.setDelFlag(true);
        announcement.setDelTime(new Date());
        announcementMapper.updateByPrimaryKeySelective(announcement);
    }
}
