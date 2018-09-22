package com.aws.springcloud.getewayzuul.controller;

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

    /**
     * 当Filter过滤器发生zuulException时会转发到此
     * @return
     */
    @GetMapping("/error")
    public String error(){
        return "error";
    }

}
