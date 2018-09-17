package com.aws.springcloud.movie.controller;

import com.aws.springcloud.movie.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@RestController
public class MovieController {

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/movie/{id}")
    public String findUserById(@PathVariable Integer id){
        String results = restTemplate.getForObject("http://microservice-provider-user/simple/" + id, String.class);
        return results;
    }

    @GetMapping("/choose")
    public String choose(){
        ServiceInstance serviceInstance1 = loadBalancerClient.choose("microservice-provider-user");
        ServiceInstance serviceInstance2 = loadBalancerClient.choose("microservice-provider-user2");

        System.out.println("111:" + serviceInstance1.getServiceId() + ";" + serviceInstance1.getHost() + ":" + serviceInstance1.getPort());
        System.out.println("222:" + serviceInstance2.getServiceId() + ":" + serviceInstance2.getHost() + ":" + serviceInstance2.getPort());
        return null;
    }

    @GetMapping("/testList")
    public List<User> getAllUser(){
        //在springcloud旧版本中会出现异常而调用失败，在2.0以后的版本中已经修复
//        List<User> userList = restTemplate.getForObject("http://microservice-provider-user/getAll", List.class);

        //需要这样的调用方式：
        User[] users = restTemplate.getForObject("http://microservice-provider-user/getAll", User[].class);
        List<User> userList2 = Arrays.asList(users);
        return userList2;
    }

}
