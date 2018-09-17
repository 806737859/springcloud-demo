package com.aws.springcloud.movie.controller;

import com.aws.springcloud.movie.entity.User;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class MovieController_ {
    @Autowired
    private RestTemplate restTemplate;

    //一般情况下不需要配置，当出现找不到上下文的时候配置
//    @HystrixCommand(fallbackMethod = "findUserByIdFallBack") // 声明该方法可能会调用失败，并做失败处理
    @HystrixCommand(fallbackMethod = "findUserByIdFallBack", commandProperties = {@HystrixProperty(name = "execution.isolation.strategy", value="SEMAPHORE")}) // 设置隔离策略，默认是THREAD开启一个新的线程，THREAD就是使用当前调用者的线程
    @GetMapping("/movie/{id}")
    public User findUserById(@PathVariable Integer id){
        User user = restTemplate.getForObject("http://microservice-provider-user/simple/" + id, User.class);
        return user;
    }

    public User findUserByIdFallBack(Integer id){
        User user = new User();
        user.setId(-1);
        return user;
    }
}
