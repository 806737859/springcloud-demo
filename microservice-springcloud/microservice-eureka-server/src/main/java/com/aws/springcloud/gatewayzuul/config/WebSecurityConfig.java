package com.aws.springcloud.gatewayzuul.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * 关闭令牌校验，否则服务无法注册到eureka
 */
@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //参考：https://blog.csdn.net/weixin_39913200/article/details/80845867
//        http.csrf().disable(); //关闭csrf
//        http.authorizeRequests().anyRequest().authenticated().and().httpBasic(); //开启认证

        //官网：12.7节 关闭csrf
        http.csrf().ignoringAntMatchers("/eureka/**");
        super.configure(http);
    }

}