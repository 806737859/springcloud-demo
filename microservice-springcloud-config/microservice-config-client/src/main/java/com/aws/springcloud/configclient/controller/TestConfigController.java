package com.aws.springcloud.configclient.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestConfigController {

    @Value("${myConfig}")
    private String myConfig;

    /**
     * 配置中心不同的配置文件的myConfig不同
     * @return
     */
    @GetMapping("/getMyConfig")
    public String getPort(){
        return myConfig;
    }

}
