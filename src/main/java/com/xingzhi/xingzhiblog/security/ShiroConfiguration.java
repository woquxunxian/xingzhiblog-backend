package com.xingzhi.xingzhiblog.security;

/**
 * @program: xingzhiblog
 * @description: shiro的配置类
 * @author: 行之
 * @create: 2021-01-19 10:10
 **/

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * shiro配置文件
 */
@Slf4j
@Configuration
public class ShiroConfiguration {

    // 认证开关
    @Value("${jwt.auth}")
    private boolean auth;

    // 配置拦截器
    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        String openAuth = auth ? "auth" : "anon";
        Map<String, Filter> filters = new HashMap<>();
        filters.put("auth", new AuthFilter());
        shiroFilterFactoryBean.setFilters(filters);
        LinkedHashMap<String, String> filterMap = new LinkedHashMap<>();
        // 查询接口放行
        filterMap.put("/api/user/login", "anon");
        filterMap.put("/api/info/all", "anon");
        filterMap.put("/api/timeline/all", "anon");
        filterMap.put("/api/wx/login", "anon");
        filterMap.put("/api/article/all", "anon");
        filterMap.put("/api/article/comment/**", "anon");
        filterMap.put("/api/article/comment/parent", "anon"); // 1
        filterMap.put("/api/article/content", "anon");
        filterMap.put("/api/article/like/number", "anon"); // 1
        filterMap.put("/api/article/like/status", "anon");
        filterMap.put("/api/article/search", "anon");
        filterMap.put("/api/article/unlike/number", "anon"); // 1
        filterMap.put("/api/article/view/number", "anon");
        filterMap.put("/api/tag/**", "anon");
        // swagger接口文档放行
        filterMap.put("/swagger-ui/**", "anon");
        filterMap.put("/swagger-resources/**", "anon");
        filterMap.put("/v3/api-docs/**", "anon");
        // druid数据源放行
        filterMap.put("/druid/**", "anon");
        filterMap.put("/**", openAuth);
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        return shiroFilterFactoryBean;
    }

    // SecurityManager安全管理器
    @Bean
    public DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(userRealm());
        return securityManager;
    }

    // 自定义身份认证realm
    @Bean
    public AuthRealm userRealm() {
        return new AuthRealm();
    }

    @Bean("lifecycleBeanPostProcessor")
    //管理shiro生命周期
    public static LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    // shiro注解支持
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }
}

