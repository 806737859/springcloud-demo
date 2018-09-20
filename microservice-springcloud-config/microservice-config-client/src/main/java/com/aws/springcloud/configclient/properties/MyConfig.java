package com.aws.springcloud.configclient.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 自定义属性
 */
@Component
public class MyConfig {

    @Value("${myConfig.application}")
    private String application;

    @Value("${myConfig.gitRepository}")
    private String gitRepository;

    @Value("${myConfig.label}")
    private String label;

    @Value("${myConfig.profile}")
    private String profile;

    @Override
    public String toString() {
        return "myConfig{" +
                "application='" + application + '\'' +
                ", gitRepository='" + gitRepository + '\'' +
                ", label='" + label + '\'' +
                ", profile='" + profile + '\'' +
                '}';
    }
}
