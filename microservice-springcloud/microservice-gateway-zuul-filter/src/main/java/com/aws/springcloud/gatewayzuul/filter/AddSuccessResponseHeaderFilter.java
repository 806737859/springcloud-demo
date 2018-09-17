package com.aws.springcloud.gatewayzuul.filter;

import com.aws.springcloud.gatewayzuul.constants.CustomFilterContants;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.POST_TYPE;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.SEND_RESPONSE_FILTER_ORDER;

/**
 * 自定义Post过滤器
 * Post过滤器通常操纵响应。
 * 下面的过滤器添加一个随机UUID作为X-Success头部（成功响应头）
 */
public class AddSuccessResponseHeaderFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return POST_TYPE;
    }

    @Override
    public int filterOrder() {
        //在SendResponseFilter过滤器之前运行
        return SEND_RESPONSE_FILTER_ORDER - 1;
    }

    @Override
    public boolean shouldFilter() {
        //在该过滤器之前没有发生异常，且版本有效
        RequestContext ctx = RequestContext.getCurrentContext();
        return ctx.getThrowable() == null
                && !ctx.containsKey(CustomFilterContants.RQUEST_VERSION_INVALID_FLAG);
    }

    @Override
    public Object run() {
        RequestContext context = RequestContext.getCurrentContext();
        //这个是要返回给客户端的response
        HttpServletResponse servletResponse = context.getResponse();
        //添加成功响应头
        servletResponse.addHeader(CustomFilterContants.HEADER_RESPONCE_SUCCESS_KEY, UUID.randomUUID().toString());
        return null;
    }
}
