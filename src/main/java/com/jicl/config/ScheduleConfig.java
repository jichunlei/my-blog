package com.jicl.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.concurrent.*;

/**
 * 定时任务配置
 *
 * @author : xianzilei
 * @date : 2020/1/7 08:31
 */
@Configuration
@EnableScheduling
public class ScheduleConfig implements SchedulingConfigurer {
    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        scheduledTaskRegistrar.setScheduler(taskExecutor());
    }

    /**
     * 功能描述: 自定义线程池
     *
     * @return java.util.concurrent.ExecutorService
     * @author xianzilei
     * @date 2020/1/7 8:40
     **/
    @Bean(destroyMethod = "shutdown")
    public ExecutorService taskExecutor() {
        return Executors.newScheduledThreadPool(10);
    }
}
