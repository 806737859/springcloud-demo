package com.aws.springcloud.movie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableCircuitBreaker   //开启断路器
@EnableFeignClients //启用feign客户端，用于注册feign接口
@SpringBootApplication
public class MicroserviceConsumerMovieFeignWithHystrixApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroserviceConsumerMovieFeignWithHystrixApplication.class, args);
    }
}
