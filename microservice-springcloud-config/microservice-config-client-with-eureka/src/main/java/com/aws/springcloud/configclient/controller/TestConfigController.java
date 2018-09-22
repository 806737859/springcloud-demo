package com.aws.springcloud.configclient.controller;

import com.aws.springcloud.configclient.properties.MyConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestConfigController {

    @Autowired
    private MyConfig myConfig;

    /**
     * 配置中心不同的配置文件的myConfig不同
     * @return
     */
    @GetMapping("/getMyConfig")
    public String getPort(){
        return myConfig.toString();
    }

}
