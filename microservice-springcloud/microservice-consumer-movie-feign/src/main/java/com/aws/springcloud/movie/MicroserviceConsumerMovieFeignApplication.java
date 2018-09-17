package com.aws.springcloud.movie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients //启用feign客户端
@SpringBootApplication
public class MicroserviceConsumerMovieFeignApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroserviceConsumerMovieFeignApplication.class, args);
    }
}
