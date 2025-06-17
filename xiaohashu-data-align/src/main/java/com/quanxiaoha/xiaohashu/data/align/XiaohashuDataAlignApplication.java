package com.quanxiaoha.xiaohashu.data.align;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author zpstart
 * @version 1.0
 * @date 2025-06-12 17:35
 */
@SpringBootApplication
@EnableFeignClients(basePackages = "com.quanxiaoha.xiaohashu")
@MapperScan("com.quanxiaoha.xiaohashu.data.align.domain.mapper")
public class XiaohashuDataAlignApplication {

    public static void main(String[] args) {
        SpringApplication.run(XiaohashuDataAlignApplication.class, args);
    }
}
