package com.quanxiaoha.xiaohashu.user.biz;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author zpstart
 * @version 1.0
 * @date 2025-06-05 14:36
 */
@SpringBootApplication
@EnableFeignClients(basePackages = "com.quanxiaoha.xiaohashu")
@MapperScan("com.quanxiaoha.xiaohashu.user.biz.domain.mapper")
public class XiaohashuUserBizApplication {

    public static void main(String[] args) {
        SpringApplication.run(XiaohashuUserBizApplication.class, args);
    }

}