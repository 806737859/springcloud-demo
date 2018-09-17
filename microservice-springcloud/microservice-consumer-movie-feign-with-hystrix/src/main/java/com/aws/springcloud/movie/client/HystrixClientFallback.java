package com.aws.springcloud.movie.client;

import com.aws.springcloud.movie.entity.User;
import org.springframework.stereotype.Component;

/**
 * 失败回调
 */
@Component
public class HystrixClientFallback implements UserFeignClient {
    @Override
    public User findById(Integer id) {
        User user = new User();
        user.setId(-1);
        return user;
    }
}
