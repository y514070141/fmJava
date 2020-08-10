package com.fmjava.core.controller;

import com.fmjava.core.pojo.entity.Result;
import com.fmjava.core.util.FastDfsClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/upload")
public class uploadFileController {

    @Value("${FILE_SERVER_URL}")
    private String file_server_url;

    @RequestMapping("/uploadFile")
    public Result uploadFile(MultipartFile file)throws Exception{
//        System.out.println(file);
        try{
            FastDfsClient fastDfsClient = new FastDfsClient("classpath:fastDFS/fdfs_client.conf");
            String path=fastDfsClient.uploadFile(file.getBytes(),file.getOriginalFilename(),file.getSize());
            String url=file_server_url+path;
            return new Result(url,true);
        }catch (Exception e){
            return new Result("上传失败",true);

        }
    }


}
