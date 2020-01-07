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
     * 功能描述: 同步博客信息至es中，同步周期（3分钟）
     *
     * @return void
     * @author xianzilei
     * @date 2020/1/7 8:56
     **/
    @Scheduled(fixedDelay = 3 * ONE_MINUTE)
    public void syncBlogToEs() {
        log.info("执行博客信息同步至ES中>>>>>>start");
        long start = System.currentTimeMillis();
        Integer count = blogService.syncBlogToEs();
        long end = System.currentTimeMillis();
        log.info("执行博客信息同步至ES中>>>>>>end，共更新数据：{}条，执行时间：{}s", count, ((float) (end - start)) / 1000);
    }
}
