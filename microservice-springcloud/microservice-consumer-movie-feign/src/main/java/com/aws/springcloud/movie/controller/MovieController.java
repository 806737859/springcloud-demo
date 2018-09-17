package com.aws.springcloud.movie.controller;

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

    @GetMapping("/movie/{id}")
    public User findUserById(@PathVariable Integer id){
        return userFeignClient.findById(id);
    }

    //方法调用失败
    @GetMapping("/movie/getUser")
    public User getUser(User user){
        return userFeignClient.getUser(user);
    }

    @PostMapping("/movie/postUser")
    public User postUser(User user){
        return userFeignClient.postUser(user);
    }
}
