package com.quanxiaoha.framework.biz.context.config;

import com.quanxiaoha.framework.biz.context.interceptor.FeignRequestInterceptor;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * Feign 请求拦截器自动配置
 * @author zpstart
 * @version 1.0
 * @date 2025-06-05 16:26
 */
@AutoConfiguration
public class FeignContextAutoConfiguration {
    @Bean
    public FeignRequestInterceptor feignRequestInterceptor() {
        return new FeignRequestInterceptor();
    }
}
