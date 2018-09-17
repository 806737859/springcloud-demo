package com.aws.springcloud.movie.config;

import java.lang.annotation.*;

/**
 * 声明一个rinbbon配置
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CustomRibbon {
}
