package com.aws.springcloud.gatewayzuul;

import com.aws.springcloud.gatewayzuul.filter.AddSuccessResponseHeaderFilter;
import com.aws.springcloud.gatewayzuul.filter.OkHttpRoutingFilter;
import com.aws.springcloud.gatewayzuul.filter.VersionPreFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

/**
 * 参考：
 * http://www.xdlysk.com/archives/448
 */
@EnableZuulProxy    //开启zuul网关代理
@SpringBootApplication
public class MicroserviceGatewayZuulFilterApplication {
    public static void main(String[] args) {
        SpringApplication.run(MicroserviceGatewayZuulFilterApplication.class,args);
    }

    @Bean
    public AddSuccessResponseHeaderFilter addSuccessResponseHeaderFilter(){
        return new AddSuccessResponseHeaderFilter();
    }

    @Bean
    public VersionPreFilter versionPreFilter(){
        return new VersionPreFilter();
    }

    @Bean
    public OkHttpRoutingFilter okHttpRoutingFilter(){
        return new OkHttpRoutingFilter();
    }
}
