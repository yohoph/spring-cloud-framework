package com.jmsw.framework.core.autoconfig;

import com.jmsw.framework.core.cache.RedisTemplateBuilder;
import com.jmsw.framework.core.lock.DistributedLockManager;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
@ConditionalOnClass(RedisTemplate.class)
public class RedisAutoConfig {
    @Bean
    public RedisTemplateBuilder redisTemplateBuilder() {
        return new RedisTemplateBuilder();
    }

    @Bean
    public DistributedLockManager distbutedLockManager() {
        return new DistributedLockManager();
    }
}
