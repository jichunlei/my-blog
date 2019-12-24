package com.jicl.service;

import com.github.pagehelper.PageInfo;
import com.jicl.entity.Announcement;

/**
 * 公告管理服务层接口
 *
 * @author : xianzilei
 * @date : 2019/12/17 19:49
 */
public interface AnnouncementService {
    /**
     * 功能描述: 分页查询公告信息
     *
     * @param pageNum 1
     * @param pageSize 2
     * @return com.github.pagehelper.PageInfo<com.jicl.entity.Announcement>
     * @author xianzilei
     * @date 2019/12/17 19:54
     **/
    PageInfo<Announcement> page(Integer pageNum, Integer pageSize);

    /**
     * 功能描述: 公告详情查看
     *
     * @param id 1
     * @return com.jicl.entity.Announcement
     * @author xianzilei
     * @date 2019/12/24 8:36
     **/
    Announcement getAnnc(Integer id);
}
