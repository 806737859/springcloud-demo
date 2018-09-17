package com.aws.springcloud.movie.client;

import com.aws.springcloud.movie.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 指定失败回调函数
 * 还可以 fallback = HystrixClientFallback.class 但是不能同时使用，会冲突
 */
@FeignClient(name = "microservice-provider-user", fallbackFactory = HystrixClientFallbackFactory.class)
public interface UserFeignClient {
    @GetMapping("/simple/{id}")
    public User findById(@PathVariable("id") Integer id);
}

