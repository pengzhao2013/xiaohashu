package com.quanxiaoha.xiaohashu.count.biz;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author zpstart
 * @version 1.0
 * @date 2025-06-10 12:07
 */
@SpringBootApplication
@MapperScan("com.quanxiaoha.xiaohashu.count.biz.domain.mapper")
public class XiaohashuCountBizApplication {

    public static void main(String[] args) {
        SpringApplication.run(XiaohashuCountBizApplication.class, args);
    }
}
