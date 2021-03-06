package com.xingzhi.xingzhiblog.article;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

//@EnableDubbo
@EnableRabbit
@EnableDiscoveryClient
@SpringBootApplication
@MapperScan("com.xingzhi.xingzhiblog.article.dao")
@EnableFeignClients(basePackages = "com.xingzhi.xingzhiblog.article.feign")
public class XingzhiblogArticleApplication {

    public static void main(String[] args) {
        SpringApplication.run(XingzhiblogArticleApplication.class, args);
    }

}
