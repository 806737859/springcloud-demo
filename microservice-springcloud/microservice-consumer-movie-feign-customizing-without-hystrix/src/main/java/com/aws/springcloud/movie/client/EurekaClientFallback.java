package com.aws.springcloud.movie.client;

import org.springframework.stereotype.Component;

/**
 * 失败回调
 */
@Component
public class EurekaClientFallback implements EurekaFeignClient {

    @Override
    public String getServiceInfoFromEurekaServerByServiceName(String serviceName) {
        return null;
    }
}
