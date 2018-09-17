package com.aws.springcloud.gatewayzuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

@EnableTurbine  //开启集群监控
@SpringBootApplication
public class MicroserviceHystrixTurbineApplication {
    public static void main(String[] args) {
        SpringApplication.run(MicroserviceHystrixTurbineApplication.class, args);
    }
}
