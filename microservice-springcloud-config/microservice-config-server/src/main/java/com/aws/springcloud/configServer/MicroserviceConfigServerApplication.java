package com.aws.springcloud.configServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 *
 * 映射规则：
 * application：需要用到远程配置的微服务（config-client）的应用名(spring.application.name 或 spring.cloud.config.name)
 * profile：需要用到远程配置的微服务（config-client）的spring.profiles.active   如果存在多个profile,以最后一个为准
 * label：需要用到远程配置的微服务（config-client）的spring.cloud.config.label
 * /{application}/{profile}[/{label}]               ㈠   获取{label}分支下匹配到的所有文件信息
 * /{application}-{profile}.yml                     ㈡   获取在master分支下匹配的yml文件内容
 * /{label}/{application}-{profile}.yml             ㈢   获取在{label}分支下匹配的yml文件内容
 * /{application}-{profile}.properties              ㈣   获取在master分支下匹配的properties文件内容
 * /{label}/{application}-{profile}.properties      ㈤   获取在{label}分支下匹配的properties文件内容
 *
 * 假设一个微服务的配置：name=movie-service，profile=dev，label=feature001
 * 匹配优先级(这里以yml为例，properties是一样的)：
 * 1. 全匹配，http://localhost:8080/feature001/movie-service-dev.yml    ——>     ConfigServer后端存储feature001分支的movie-service-dev.yml
 * 2. 半匹配，http://localhost:8080/feature001/xxxxx-dev.yml            ——>     ConfigServer后端存储feature001分支的*-dev.yml
 * 3. 没有匹配，http://localhost:8080/feature001/xxxxx-xxxx.yml          ——>     ConfigServer后端存储feature001分支的application.yml
 *
 * 注意：如果ConfigServer后端存储是git,label默认就是master
 */
@EnableConfigServer
@SpringBootApplication
public class MicroserviceConfigServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(MicroserviceConfigServerApplication.class,args);
    }
}
