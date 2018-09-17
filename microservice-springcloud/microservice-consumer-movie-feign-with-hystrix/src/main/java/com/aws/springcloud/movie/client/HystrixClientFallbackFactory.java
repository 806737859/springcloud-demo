package com.aws.springcloud.movie.client;

import com.aws.springcloud.movie.entity.User;
import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 失败回调工厂
 */
@Component
public class HystrixClientFallbackFactory implements FallbackFactory<UserFeignClient> {

    private static final Logger LOGGER = LoggerFactory.getLogger(HystrixClientFallbackFactory.class);

    @Override
    public UserFeignClient create(Throwable cause) {
        //打印出错日志
        LOGGER.info("fallback; reason was: " + cause.getMessage());

        //lambda表达式
        return id -> {
            User user = new User();
            user.setId(-1);
            return user;
        };

       /* return new UserFeignClient() {
            @Override
            public User findById(Integer id) {
                User user = new User();
                user.setId(-1);
                return user;
            }
        };*/
    }
}
