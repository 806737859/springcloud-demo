package com.aws.springcloud.movie.client;

import com.aws.springcloud.movie.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient("microservice-provider-user")
public interface UserFeignClient {
    //坑:在使用feign的时候，@PathVariable必须有value值
    @GetMapping("/simple/{id}")
    public User findById(@PathVariable("id") Integer id);

    //坑:调用失败，只要参数为复杂对象，即使声明为Get请求，也会以Post方式发送请求
    @GetMapping("/getUser")
    public User getUser(User user);

    @PostMapping("/postUser")
    public User postUser(User user);
}

