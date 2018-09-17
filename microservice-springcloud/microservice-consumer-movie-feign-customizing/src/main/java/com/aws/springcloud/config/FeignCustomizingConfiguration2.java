package com.aws.springcloud.config;

import feign.auth.BasicAuthRequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 配置不能被springboot扫描到，否则会被所有的feignclient共享
 */
@Configuration
public class FeignCustomizingConfiguration2 {
    /**
     * 安全配置，当eurekaServer设置用户名密码,feign接口为url时需要配置
     * @return
     */
    @Bean
    public BasicAuthRequestInterceptor basicAuthRequestInterceptor() {
        return new BasicAuthRequestInterceptor("user", "password123");
    }
}
