package com.quanxiaoha.xiaohashu.search.biz;

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
@MapperScan("com.quanxiaoha.xiaohashu.search.biz.domain.mapper")
public class XiaohashuSearchBizApplication {
    public static void main(String[] args) {
        SpringApplication.run(XiaohashuSearchBizApplication.class, args);
    }
}