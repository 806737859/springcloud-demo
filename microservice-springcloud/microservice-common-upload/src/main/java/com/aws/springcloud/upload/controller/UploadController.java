package com.aws.springcloud.upload.controller;

import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Controller
public class UploadController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    /**
     * 测试上传文件（未做特殊处理）
     * 测试页面：localhost:8050/
     * 使用curl测试：   curl --form "{参数名}=@{文件路径}" {请求路径}
     * curl --form "file=@D:\教程\springcloud\Spring Cloud-2\kkk.avi" http://localhost:8050/upload
     * @param file
     * @return  文件在服务器上的绝对路径
     * @throws Exception
     */
    @PostMapping("/upload")
    @ResponseBody
    public String upload(@RequestParam(value = "file",required = true)MultipartFile file) throws Exception{
        byte[] bytes = file.getBytes();
        File fileToSave = new File(file.getOriginalFilename());
        FileCopyUtils.copy(bytes,fileToSave);
        return fileToSave.getAbsolutePath();
    }
}
