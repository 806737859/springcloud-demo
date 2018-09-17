package com.aws.springcloud.movie.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

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

    /**
     * 调试指定的微服务ribbon配置是否影响到其它的微服务
     * @return
     */
    @GetMapping("/choose")
    public String choose(){
        ServiceInstance serviceInstance1 = loadBalancerClient.choose("microservice-provider-user");
        ServiceInstance serviceInstance2 = loadBalancerClient.choose("microservice-provider-user2");

        System.out.println("111:" + serviceInstance1.getServiceId() + ";" + serviceInstance1.getHost() + ":" + serviceInstance1.getPort());
        System.out.println("222:" + serviceInstance2.getServiceId() + ":" + serviceInstance2.getHost() + ":" + serviceInstance2.getPort());
        return null;
    }

}
