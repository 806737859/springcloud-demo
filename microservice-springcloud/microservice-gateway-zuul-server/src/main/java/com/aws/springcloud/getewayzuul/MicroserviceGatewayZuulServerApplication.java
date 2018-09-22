package com.aws.springcloud.getewayzuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulServer;

@EnableZuulServer
@SpringBootApplication
public class MicroserviceGatewayZuulServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(MicroserviceGatewayZuulServerApplication.class,args);
    }
}
