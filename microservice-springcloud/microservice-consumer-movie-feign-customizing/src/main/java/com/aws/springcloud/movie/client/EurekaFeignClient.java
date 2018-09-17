package com.aws.springcloud.movie.client;

import com.aws.springcloud.config.FeignCustomizingConfiguration2;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * url访问时，如果访问的服务设置了密码，需要在配置文件中加入密码设置
 */
@FeignClient(name = "eureka", url = "http://localhost:8761", configuration = FeignCustomizingConfiguration2.class) //使用url时，name必须填写，作为名称标识
public interface EurekaFeignClient {

    @GetMapping("/eureka/apps/{serviceName}")
    public String getServiceInfoFromEurekaServerByServiceName(@PathVariable("serviceName") String serviceName);
}
