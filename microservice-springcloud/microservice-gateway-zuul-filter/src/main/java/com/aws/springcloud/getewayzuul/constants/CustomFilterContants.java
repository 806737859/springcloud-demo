package com.aws.springcloud.getewayzuul.constants;

public class CustomFilterContants {

    /**
     * 响应头key-成功响应
     */
    public static final String HEADER_RESPONCE_SUCCESS_KEY = "X-Success";

    /**
     * 请求头key-请求版本
     */
    public static final String HEADER_REQUEST_VERSION_KEY = "X-Version";

    /**
     * 请求上下文key-请求版本无效标识
     * 如果请求头X-Version无效，VersionPreFilter会RequetContext中加入该key
     * 如果RequetContext存在该key，则不会经过AddRespon
     */
    public static final String RQUEST_VERSION_INVALID_FLAG = "versionInvalid";
}
