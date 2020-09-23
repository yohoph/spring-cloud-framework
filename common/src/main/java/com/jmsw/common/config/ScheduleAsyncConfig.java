package com.jmsw.common.config;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author yehao
 * @version 1.0
 * @date 2020/9/16 10:55
 * @Description 定时器配置
 */
@Configuration
public class ScheduleAsyncConfig implements SchedulingConfigurer {

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {

        taskRegistrar.setScheduler(new ScheduledThreadPoolExecutor(10,
                new BasicThreadFactory.Builder().namingPattern("Scheduling-%d").daemon(true).build()));
    }

    @Bean("SchedulePool")
    public ThreadPoolExecutor schedulePool() {
        return new ScheduledThreadPoolExecutor(10,
                new BasicThreadFactory.Builder().namingPattern("Scheduling-%d").daemon(true).build());
    }

}
