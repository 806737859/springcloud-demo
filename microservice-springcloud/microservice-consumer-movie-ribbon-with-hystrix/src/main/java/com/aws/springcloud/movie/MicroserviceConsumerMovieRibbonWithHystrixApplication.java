package com.aws.springcloud.movie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@EnableCircuitBreaker //开启断路器
@SpringBootApplication
public class MicroserviceConsumerMovieRibbonWithHystrixApplication {

    @LoadBalanced   // 具备负载均衡的能力
    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(MicroserviceConsumerMovieRibbonWithHystrixApplication.class, args);
    }
}
