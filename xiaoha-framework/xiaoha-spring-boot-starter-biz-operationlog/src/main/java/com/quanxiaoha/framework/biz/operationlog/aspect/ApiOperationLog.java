package com.quanxiaoha.framework.biz.operationlog.aspect;

import java.lang.annotation.*;

/**
 * @author zpstart
 * @version 1.0
 * @date 2025-06-01 22:39
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
public @interface ApiOperationLog {
    /**
     * API 功能描述
     *
     * @return
     */
    String description() default "";
}