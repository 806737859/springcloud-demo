### SpringCloud 核心模块的使用
- Spring Cloud Netflix Eureka
- Spring Cloud Netflix Hystrix
- Spring Cloud Netflix Zuul
- Spring Cloud Netflix Ribbon
- Spring Cloud Netflix Turbine
- Spring Cloud OpenFeign Feign

#### 注意事项
1. 所有端点访问必须加入以下依赖，url格式:http://<host>:<port>/actuator/**
```
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
```
参考地址：https://docs.spring.io/spring-boot/docs/2.0.5.RELEASE/reference/htmlsingle/#production-ready
2. 服务重构相关
绞杀者模式：SpringCloud文档章节：18.7 Strangulation Patterns and Local Forwards
