package com.quanxiaoha.xiaohashu.comment.biz.config;

import org.apache.rocketmq.spring.autoconfigure.RocketMQAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * RocketMQ 配置
 *
 * @Author: zpstart
 * @Date: 2025-06-17 13:46
 */
@Configuration
@Import(RocketMQAutoConfiguration.class)
public class RocketMQConfig {
}