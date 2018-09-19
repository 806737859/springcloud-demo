// 运行node的命令    node node-server.js
var http = require('http');
var url = require('url');
var path = require('path');

http.createServer(function (request, response) {
    // 发送 HTTP 头部
    // HTTP 状态值: 200 : OK
    // 内容类型: text/plain
    response.writeHead(200, {'Content-Type': 'application/json'});
    //获取请求路径
    var pathname = url.parse(request.url).pathname;
    //访问http://localhost:8060/，返回{"index":"Hello World"}
    if(pathname === '/'){
        response.end(JSON.stringify({"index":"Hello World"}));
    }else if(pathname === '/health.json'){
        //返回健康检查状态
        response.end(JSON.stringify({"status":"UP"}));
    }else {
        response.end("404");
    }
}).listen(8070);    //监听8888端口

// 终端打印如下信息
console.log('Server running at http://127.0.0.1:8888/');