package com.xingzhi.xingzhiblog.info;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
@MapperScan("com.xingzhi.xingzhiblog.info.dao")
public class XingzhiblogInfoApplication {

    public static void main(String[] args) {
        SpringApplication.run(XingzhiblogInfoApplication.class, args);
    }

}
