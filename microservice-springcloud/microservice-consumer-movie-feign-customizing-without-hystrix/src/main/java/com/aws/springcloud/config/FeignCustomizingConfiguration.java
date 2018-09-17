package com.aws.springcloud.config;

import feign.Contract;
import feign.Feign;
import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * 配置不能被springboot扫描到，否则会被所有的feignclient共享
 */
@Configuration
public class FeignCustomizingConfiguration {
    /**
     * 使用feign默认协议
     * @return
     */
    @Bean
    public Contract feignContract() {
        return new feign.Contract.Default();
    }

    /**
     * 配置feign日志级别，默认为NONE
     * @return
     */
    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    /**
     * 使用Feign原生的构造器(不使用熔断机制)，默认使用hystrix的构造器（开启熔断机制）
     * @return
     */
    @Bean
    @Scope("prototype")
    public Feign.Builder feignBuilder() {
        return Feign.builder();
    }
}
