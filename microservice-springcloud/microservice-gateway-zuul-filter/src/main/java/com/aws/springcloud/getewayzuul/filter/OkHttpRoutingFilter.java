package com.aws.springcloud.getewayzuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import okhttp3.*;
import okhttp3.internal.http.HttpMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.ProxyRequestHelper;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.StreamUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.ROUTE_TYPE;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.SIMPLE_HOST_ROUTING_FILTER_ORDER;

/**
 * 官方案例，没有测通
 */
public class OkHttpRoutingFilter extends ZuulFilter {
    @Autowired
    private ProxyRequestHelper helper;

    @Override
    public String filterType() {
        return ROUTE_TYPE;
    }

    @Override
    public int filterOrder() {
        return SIMPLE_HOST_ROUTING_FILTER_ORDER - 1;
    }

    @Override
    public boolean shouldFilter() {
        return RequestContext.getCurrentContext().getRouteHost() != null
                && RequestContext.getCurrentContext().sendZuulResponse();
    }

    @Override
    public Object run() {
        try {
            OkHttpClient httpClient = new OkHttpClient.Builder()
                    // customize
                    .build();

            RequestContext context = RequestContext.getCurrentContext();
            HttpServletRequest request = context.getRequest();

            String method = request.getMethod();

            String uri = this.helper.buildZuulRequestURI(request);

            //封装请求头
            Headers.Builder headers = new Headers.Builder();
            Enumeration<String> headerNames = request.getHeaderNames();
            while (headerNames.hasMoreElements()) {
                String name = headerNames.nextElement();
                Enumeration<String> values = request.getHeaders(name);

                while (values.hasMoreElements()) {
                    String value = values.nextElement();
                    headers.add(name, value);
                }
            }

            //封装请求体
            InputStream inputStream = request.getInputStream();

            RequestBody requestBody = null;
            if (inputStream != null && HttpMethod.permitsRequestBody(method)) {
                MediaType mediaType = null;
                if (headers.get("Content-Type") != null) {
                    mediaType = MediaType.parse(headers.get("Content-Type"));
                }
                requestBody = RequestBody.create(mediaType, StreamUtils.copyToByteArray(inputStream));
            }

            Request.Builder builder = new Request.Builder()
                    .headers(headers.build())
                    .url(uri)
                    .method(method, requestBody);

            //执行请求获取响应
            Response response = httpClient.newCall(builder.build()).execute();

            //封装响应头
            LinkedMultiValueMap<String, String> responseHeaders = new LinkedMultiValueMap<>();

            for (Map.Entry<String, List<String>> entry : response.headers().toMultimap().entrySet()) {
                responseHeaders.put(entry.getKey(), entry.getValue());
            }

            //将响应封装RequestContext中，由后续的Post过滤进一步封装成响应给客户端的response
            this.helper.setResponse(response.code(), response.body().byteStream(),
                    responseHeaders);
            context.setRouteHost(null); // prevent SimpleHostRoutingFilter from running
        }catch (Exception e){
        }
        return null;
    }
}