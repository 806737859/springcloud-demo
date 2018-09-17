package com.aws.springcloud.movie;

import com.aws.springcloud.movie.config.CustomRibbon;
import com.aws.springcloud.movie.config.RibbonConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.client.RestTemplate;

@RibbonClient(name = "microservice-provider-user", configuration = RibbonConfiguration.class) //自定义ribbon客户端，默认配置是RibbonClientConfiguration
//@RibbonClients(defaultConfiguration = RibbonConfiguration.class) //所有ribbon客户端都使用该配置
@ComponentScan(excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, value = CustomRibbon.class)}) //扫描过滤
@SpringBootApplication
public class MicroserviceConsumerMovieRibbonApplication {

    @LoadBalanced   // 具备负载均衡的能力
    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(MicroserviceConsumerMovieRibbonApplication.class, args);
    }
}
