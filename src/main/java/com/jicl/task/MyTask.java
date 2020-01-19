package com.jicl.task;

import com.jicl.service.BlogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 定时任务
 *
 * @author : xianzilei
 * @date : 2020/1/7 08:41
 */
@Component
@Slf4j
public class MyTask {
    public final static long ONE_MINUTE = 60 * 1000;

    @Autowired
    private BlogService blogService;

    /**
     * 功能描述: 同步博客信息至es中：同步周期（3分钟）
     *
     * @author xianzilei
     * @date 2020/1/19 10:20
     **/
    @Scheduled(fixedDelay = 3 * ONE_MINUTE)
    public void syncBlogToEs() {
        log.info("执行博客信息同步至ES中>>>>>>start");
        long start = System.currentTimeMillis();
        Integer count = blogService.syncBlogToEs();
        long end = System.currentTimeMillis();
        log.info("执行博客信息同步至ES中>>>>>>end，共更新数据：{}条，执行时间：{}s", count, ((float) (end - start)) / 1000);
    }

    /**
     * 功能描述: 同步博客浏览量和评论数信息（from Redis）：同步周期每天凌晨2:00
     *
     * @author xianzilei
     * @date 2020/1/19 10:20
     **/
    @Scheduled(cron = "0 50 15 * * ?")
    public void syncBlogCommentsAndViews() {
        log.info("执行同步博客浏览量和评论数任务（from Redis）>>>>>>start");
        long start = System.currentTimeMillis();
        blogService.syncBlogCommentsAndViews();
        long end = System.currentTimeMillis();
        log.info("执行同步博客浏览量和评论数任务（from Redis）>>>>>>end，执行时间：{}s", ((float) (end - start)) / 1000);
    }

}
