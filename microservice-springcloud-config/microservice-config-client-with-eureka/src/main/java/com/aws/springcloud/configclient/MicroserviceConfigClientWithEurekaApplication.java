package com.aws.springcloud.configclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MicroserviceConfigClientWithEurekaApplication {
    public static void main(String[] args) {
        SpringApplication.run(MicroserviceConfigClientWithEurekaApplication.class,args);
    }
}
