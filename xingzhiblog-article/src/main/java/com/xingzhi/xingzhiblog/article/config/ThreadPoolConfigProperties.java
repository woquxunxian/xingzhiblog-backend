package com.xingzhi.xingzhiblog.article.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "xingzhiblog.thread")
public class ThreadPoolConfigProperties {

    private Integer coreSize;  //核心线程数
    private Integer maxSize; //最大线程数
    private Integer keepAliveTime; //休眠时长

}
