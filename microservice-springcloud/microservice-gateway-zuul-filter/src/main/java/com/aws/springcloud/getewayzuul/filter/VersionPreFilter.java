package com.aws.springcloud.getewayzuul.filter;

import com.aws.springcloud.getewayzuul.constants.CustomFilterContants;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.*;

/**
 * 自定义Pre过滤器
 * Pre过滤器在RequestContext中设置数据，以便在下游过滤器中使用。
 * 版本控制过滤器，每个请求必须包含请求头（X-Version）,格式为纯数字，否则响应失败
 */
public class VersionPreFilter extends ZuulFilter {

    /**
     * 过滤器类型
     * @return
     */
    @Override
    public String filterType() {
        return PRE_TYPE;
    }

    /**
     * 过滤器顺序
     * @return
     */
    @Override
    public int filterOrder() {
        //在PreDecorationFilter过滤器之前运行
        return PRE_DECORATION_FILTER_ORDER -1;
    }

    /**
     * 执行过滤器条件
     * @return
     */
    @Override
    public boolean shouldFilter() {
        RequestContext ctx = RequestContext.getCurrentContext();
        //没有确定转发的位置和没有确定serviceId
        return !ctx.containsKey(FORWARD_TO_KEY) // a filter has already forwarded
                && !ctx.containsKey(SERVICE_ID_KEY); // a filter has already determined serviceId
    }

    /**
     * 版本控制
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        String version = request.getHeader(CustomFilterContants.HEADER_REQUEST_VERSION_KEY);
        if(!StringUtils.isNumeric(version) || "".equals(version)){
            //不会经过RibbonRoutingFilter、SimpleHostRoutingFilter以及自定义的OkHttpRoutingFilter
            ctx.setSendZuulResponse(false);
            //设置下面的参数后将不会经过PreDecorationFilter，由于没有经过PreDecorationFilter所以不会设置forward.to也就不会经过SendForwardFilter
            // 这个参数是随意设置的 = =！
            ctx.set(SERVICE_ID_KEY,"no");
            //返回状态码(错误请求)
            ctx.setResponseStatusCode(400);
            //添加请求版本无效标志，将不会经过AddSuccessResponseHeaderFilter
            ctx.set(CustomFilterContants.RQUEST_VERSION_INVALID_FLAG);
            //返回内容给客户端
            ctx.setResponseBody("{\"result\":\"request version error!\"}");// 返回错误内容
        }
        return null;
    }
}
