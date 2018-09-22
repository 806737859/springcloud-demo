package com.aws.springcloud.getewayzuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.filters.discovery.PatternServiceRouteMapper;
import org.springframework.context.annotation.Bean;

@EnableZuulProxy    //开启网关代理
@SpringBootApplication
public class MicroserviceGatewayZuulRegularExpressionApplication {

    /**
     * 动态路由配置(使用指定正则表达式路由)
     * 将正匹配servicePattern正则表达式的serviceId与routePattern（路由正则表达式）建立映射关系
     * 譬如一个微服务的serviceId是 microservice-user-v1
     * 代理的route将会是 /v1/microservice-user/**
     * @return
     */
    @Bean
    public PatternServiceRouteMapper serviceRouteMapper() {
        return new PatternServiceRouteMapper(
                "(?<name>^.+)-(?<version>v.+$)",
                "${version}/${name}");
    }

    public static void main(String[] args) {
        SpringApplication.run(MicroserviceGatewayZuulRegularExpressionApplication.class, args);
    }
}