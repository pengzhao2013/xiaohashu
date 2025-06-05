package com.quanxiaoha.xiaohashu.oss.config;

import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zpstart
 * @version 1.0
 * @date 2025-06-05 16:00
 */
@Configuration
public class FeignFormConfig {

    /**
     * SpringFormEncoder 是 Feign 提供的一个编码器，
     * 用于处理表单提交。它将对象编码为表单数据格式（如 application/x-www-form-urlencoded
     * 或 multipart/form-data），以便在 HTTP 请求中使用
     * 在选择表单数据编码格式时，如果只是提交简单的键值对且不包含文件，一般使用application/x-www-form-urlencoded；
     * 而如果有文件上传或者其他复杂数据结构需要保持原样传输，则使用multipart/form-data
     * @return
     */
    @Bean
    public Encoder feignFormEncoder() {
        return new SpringFormEncoder();
    }
}