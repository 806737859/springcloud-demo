package com.aws.springcloud.getewayzuul.fallback;

import com.netflix.hystrix.exception.HystrixTimeoutException;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@Component
public class MyFallbackProvider implements FallbackProvider {

    /**
     * 这个fallback适用于哪个路由（这里的返回值是路由id）
     * 但是返回路由id并没有什么卵用，返回的应该是serviceId
     * 返回"*" 或者 null为所有的路由作回退
     * @return
     */
    @Override
    public String getRoute() {
        // 无效 return "userRoute";
        return "microservice-provider-user";
    }

    /**
     * 网关层面,失败回退
     * @param route
     * @param cause
     * @return
     */
    @Override
    public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
        if (cause instanceof HystrixTimeoutException) {
            return response(HttpStatus.GATEWAY_TIMEOUT);
        } else {
            return response(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 创建一个失败的response对象
     * @param status
     * @return
     */
    private ClientHttpResponse response(final HttpStatus status) {
        return new ClientHttpResponse() {
            @Override
            public HttpStatus getStatusCode() throws IOException {
//                return status;
                return HttpStatus.OK;
            }

            @Override
            public int getRawStatusCode() throws IOException {
//                return status.value();
                return 200;
            }

            @Override
            public String getStatusText() throws IOException {
//                return status.getReasonPhrase();
                return "OK";
            }

            @Override
            public void close() {
            }

            @Override
            public InputStream getBody() throws IOException {
                return new ByteArrayInputStream(("fallback:" + MyFallbackProvider.this.getRoute()).getBytes());
            }

            @Override
            public HttpHeaders getHeaders() {
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                return headers;
            }
        };
    }
}
