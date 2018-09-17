package com.aws.springcloud.gatewayzuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@EnableHystrixDashboard //开启熔断器监控，访问url为/hystrix
@SpringBootApplication
public class MicroserviceHystrixDashboardApplication {
    public static void main(String[] args) {
        SpringApplication.run(MicroserviceHystrixDashboardApplication.class, args);
    }
}
