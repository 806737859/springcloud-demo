package com.aws.springcloud.gatewayzuul.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/test")
    public String testLocal(){
        return "test";
    }

    @GetMapping("/test2")
    public String testLocal2(){
        return "test2";
    }

}
