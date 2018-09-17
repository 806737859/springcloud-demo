package com.aws.springcloud.gatewayzuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class MicroserviceEurekaServerHaApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroserviceEurekaServerHaApplication.class, args);
    }
}
