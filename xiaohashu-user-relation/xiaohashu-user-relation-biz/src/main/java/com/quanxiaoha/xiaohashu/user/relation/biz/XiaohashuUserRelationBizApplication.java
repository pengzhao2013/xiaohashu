package com.quanxiaoha.xiaohashu.user.relation.biz;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author zpstart
 * @version 1.0
 * @date 2025-06-08 11:15
 */
@SpringBootApplication
@MapperScan("com.quanxiaoha.xiaohashu.user.relation.biz.domain.mapper")
@EnableFeignClients(basePackages = "com.quanxiaoha.xiaohashu")
public class XiaohashuUserRelationBizApplication {

    public static void main(String[] args) {
        SpringApplication.run(XiaohashuUserRelationBizApplication.class, args);
    }
}