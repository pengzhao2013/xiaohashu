package com.quanxiaoha.xiaohashu.search;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @Author : zpstart
 * @Date: 2025-06-15 19:57
 */
@SpringBootApplication
@EnableScheduling
@MapperScan("com.quanxiaoha.xiaohashu.search.domain.mapper")
public class XiaohashuSearchApplication {
    public static void main(String[] args) {
        SpringApplication.run(XiaohashuSearchApplication.class, args);
    }
}