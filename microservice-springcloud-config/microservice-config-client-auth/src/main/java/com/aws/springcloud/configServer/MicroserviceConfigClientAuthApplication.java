package com.aws.springcloud.configServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MicroserviceConfigClientAuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(MicroserviceConfigClientAuthApplication.class,args);
    }
}
