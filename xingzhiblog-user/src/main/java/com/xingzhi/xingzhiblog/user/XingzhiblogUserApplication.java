package com.xingzhi.xingzhiblog.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@EnableDiscoveryClient
@SpringBootApplication
@MapperScan("com.xingzhi.xingzhiblog.user.dao")
@ComponentScan(basePackages = "com.xingzhi.xingzhiblog.user")
public class XingzhiblogUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(XingzhiblogUserApplication.class, args);
    }

}
