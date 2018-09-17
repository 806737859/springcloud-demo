package com.aws.springcloud.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@EnableEurekaClient //在springcloud 2.0.4中不需要声明，是一个空注解 = =！
//@EnableDiscoveryClient //支持zk等其他注册中心
@MapperScan("com.aws.springcloud.user.dao")
@SpringBootApplication
public class MicroserviceProviderUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroserviceProviderUserApplication.class, args);
    }
}