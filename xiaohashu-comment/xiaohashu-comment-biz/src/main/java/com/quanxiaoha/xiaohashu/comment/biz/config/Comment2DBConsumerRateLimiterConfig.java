package com.quanxiaoha.xiaohashu.comment.biz.config;

import com.google.common.util.concurrent.RateLimiter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: zpstart
 * @Date: 2025-06-17 15:43
 * @Description:
 */
@Configuration
@RefreshScope
public class Comment2DBConsumerRateLimiterConfig {
    @Value("${mq-consumer.comment-to-db.rate-limit}")
    private double rateLimit;

    @Bean
    @RefreshScope
    public RateLimiter rateLimiter() {
        return RateLimiter.create(rateLimit);
    }
}