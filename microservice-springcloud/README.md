#注意事项
```
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
```
加入上述才能访问响应监控页面，但是需要注意的是:
url格式:http://\<host>:\<port>/actuator/

有关终端不能访问的参与springboot文档：50节
https://docs.spring.io/spring-boot/docs/2.0.5.RELEASE/reference/htmlsingle/#production-ready

绞杀者模式：18.7 Strangulation Patterns and Local Forwards