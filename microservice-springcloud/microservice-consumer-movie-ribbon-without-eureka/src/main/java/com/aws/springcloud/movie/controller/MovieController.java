package com.aws.springcloud.movie.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@RestController
public class MovieController {

    /**
     * ribbon客户端
     */
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
     * 使用ribbon原生api调用服务
     * @return
     */
    @GetMapping("/choose")
    public String choose() throws Exception{
        ServiceInstance serviceInstance1 = loadBalancerClient.choose("microservice-provider-user");
        System.out.println(serviceInstance1.getUri());
        URI storesUri = URI.create(String.format("http://%s:%s", serviceInstance1.getHost(), serviceInstance1.getPort()));
        System.out.println("111:" + serviceInstance1.getServiceId() + ";" + serviceInstance1.getHost() + ":" + serviceInstance1.getPort());
        return null;
    }

}
