package com.aws.springcloud.movie.config;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 不能被@SpringBootApplication扫描到，否则所有的ribbonclient都会共享这个配置
 * 方法一：不在主应用类所在包及其自包下
 * 方法二：@SpringBootApplication扫描过滤该配置，通过自定义注解的方式
 */
@CustomRibbon
@Configuration
public class RibbonConfiguration {

    /**
     * 负载均衡策略
     * @return
     */
    @Bean
    public IRule ribbonRule(){
        //随机策略
        return new RandomRule();
    }
}
