package com.aws.springcloud.config;

import feign.Contract;
import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
}
