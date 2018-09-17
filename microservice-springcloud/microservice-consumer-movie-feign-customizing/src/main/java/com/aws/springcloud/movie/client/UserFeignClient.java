package com.aws.springcloud.movie.client;

import com.aws.springcloud.config.FeignCustomizingConfiguration;
import com.aws.springcloud.movie.entity.User;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * 使用自定义Feign配置
 */
@FeignClient(name = "microservice-provider-user", configuration = FeignCustomizingConfiguration.class) // name是调用服务的serviceId
public interface UserFeignClient {
    /**
     * 在配置中使用feign的默认协议，所以只能使用feign的api
     * @param id
     * @return
     */
    @RequestLine("GET /simple/{id}")
    public User findById(@Param("id") Integer id);
}
