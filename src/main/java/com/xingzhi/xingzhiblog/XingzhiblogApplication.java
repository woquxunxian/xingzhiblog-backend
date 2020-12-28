package com.xingzhi.xingzhiblog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.xingzhi.xingzhiblog.dao")
public class XingzhiblogApplication {

    public static void main(String[] args) {
        SpringApplication.run(XingzhiblogApplication.class, args);
    }

}
