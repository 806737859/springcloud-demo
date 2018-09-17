package com.aws.springcloud.gatewayzuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * 参考：
 * http://www.xdlysk.com/archives/448
 */
@EnableZuulProxy    //开启zuul网关代理
@SpringBootApplication
public class MicroserviceGatewayZuulApplication {
    public static void main(String[] args) {
        SpringApplication.run(MicroserviceGatewayZuulApplication.class,args);
    }
}
