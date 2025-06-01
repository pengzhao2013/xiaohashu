package com.quanxiaoha.framework.biz.operationlog.config;

import com.quanxiaoha.framework.biz.operationlog.aspect.ApiOperationLogAspect;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * @author zpstart
 * @version 1.0
 * @date 2025-06-01 22:43
 */
@AutoConfiguration
public class ApiOperationLogAutoConfiguration {

    @Bean
    public ApiOperationLogAspect apiOperationLogAspect() {
        return new ApiOperationLogAspect();
    }
}
