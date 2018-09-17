package com.aws.springcloud.movie.controller;

import com.aws.springcloud.movie.client.EurekaFeignClient;
import com.aws.springcloud.movie.client.UserFeignClient;
import com.aws.springcloud.movie.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MovieController {

    @Autowired
    private UserFeignClient userFeignClient;

    @Autowired
    private EurekaFeignClient eurekaFeignClient;

    @GetMapping("/movie/{id}")
    public User findUserById(@PathVariable Integer id){
        return userFeignClient.findById(id);
    }

    /**
     * 获取eureka已经注册的指定服务的信息
     * @param serviceName
     * @return
     */
    @GetMapping("/eureka/{serviceName}")
    public String getServiceInfoFromEurekaServerByServiceName(@PathVariable String serviceName){
        return eurekaFeignClient.getServiceInfoFromEurekaServerByServiceName(serviceName);
    }
}
