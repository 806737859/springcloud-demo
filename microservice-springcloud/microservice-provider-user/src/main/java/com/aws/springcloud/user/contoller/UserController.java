package com.aws.springcloud.user.contoller;

import com.alibaba.fastjson.JSONObject;
import com.aws.springcloud.user.dao.UserMapper;
import com.aws.springcloud.user.entity.User;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.List;

@RestController
public class UserController {

    @Value("${server.port}")
    private String serverPort;

    @Autowired
    private UserMapper userMapper;

    //操作eureka-client
    @Autowired
    private EurekaClient discoveryClient;

    @GetMapping("/simple/{id}")
    public User findById(@PathVariable Integer id) throws Exception{
        System.out.println(serverPort);
//        Thread.sleep(10000);
        return userMapper.selectByPrimaryKey(id);
    }

    @GetMapping("/instance-info")
    public String serviceUrl() {
        //虚拟名称就是应用的名称，必须跟监控页面中保持一致
        InstanceInfo instance = discoveryClient.getNextServerFromEureka("MICROSERVICE-PROVIDER-USER", false);

        return instance.getHomePageUrl();
    }

    @GetMapping("/getAll")
    public List<User> getAll(){
        return userMapper.selectAll();
    }

    @PostMapping("/postUser")
    public User postUser(User user){
        return user;
    }

    @GetMapping("/getUser")
    public User getUser(User user){
        return user;
    }

    @GetMapping("/getUserByBalance")
    public List<User> getUserByBalance(Double balance){
        return userMapper.selectBybalance(balance);
    }

    @GetMapping("/retry")
    public String retry() throws Exception{
        System.out.println(serverPort);
        return "retry!!";
    }

    /**
     * 经过api网关的请求会被添加两个头信息
     * X-Forwarded-Host ：网关主机
     * X-Forwarded-Prefix：路由前缀：如/api
     * @param request
     * @return
     */
    @GetMapping("/getHeader")
    public String getHeader(HttpServletRequest request){
        JSONObject result = new JSONObject();

        Enumeration<String> headerNames =  request.getHeaderNames();
        while(headerNames.hasMoreElements()){
            String headerName = headerNames.nextElement();
            String headerValue = request.getHeader(headerName);
            result.put(headerName, headerValue);
        }

        return result.toJSONString();
    }

    @GetMapping("/getCookie")
    public String getCookie(HttpServletRequest request){
        JSONObject result = new JSONObject();

        Cookie[] cookies = request.getCookies();
        if(cookies != null && cookies.length > 0){
            for(Cookie cookie : cookies){
                result.put(cookie.getName(), cookie.getValue());
            }
        }
        return result.toJSONString();
    }
}
