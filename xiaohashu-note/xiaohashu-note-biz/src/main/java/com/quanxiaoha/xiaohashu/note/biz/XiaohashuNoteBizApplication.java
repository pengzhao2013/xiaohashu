package com.quanxiaoha.xiaohashu.note.biz;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author zpstart
 * @version 1.0
 * @date 2025-06-07 9:07
 */
@SpringBootApplication
@MapperScan("com.quanxiaoha.xiaohashu.note.biz.domain.mapper")
@EnableFeignClients(basePackages = "com.quanxiaoha.xiaohashu")
public class XiaohashuNoteBizApplication {
    public static void main(String[] args) {
        SpringApplication.run(XiaohashuNoteBizApplication.class, args);
    }
}
