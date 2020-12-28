package com.xingzhi.xingzhiblog.config;

import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @program: xingzhiblog
 * @description: Swagger3配置类
 * @author: 行之
 * @create: 2020-12-19 15:11
 **/
@Configuration
public class SwaggerConfig {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("行之Blog接口文档")
                .description("行之Blog服务接口文档-微信小程序+后台管理系统")
                .contact(new Contact("行之", "https://www.cnblogs.com/xunxian/", "1252136236@qq.com"))
                .version("1.0")
                .build();
    }
}