package com.fmjava.core.controller;

import com.fmjava.core.pojo.entity.Result;
import com.fmjava.core.util.FastDfsClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

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

    @RequestMapping("/deleteImg")
    public Result deleteImg(String url){
        try{
            String path=url.substring(file_server_url.length());
            FastDfsClient fastDfsClient = new FastDfsClient("classpath:fastDFS/fdfs_client.conf");
            Integer integer = fastDfsClient.delete_file(path);
            if(integer==0)
                return new Result("删除成功",true);
            else
                return new Result("删除失败",false);
        }catch (Exception e){
            return new Result("删除失败",false);
        }
    }

    @RequestMapping("/uploadImage")
    public Map uploadImage(MultipartFile upfile) throws Exception {
        try {
            FastDfsClient fastDFS = new FastDfsClient("classpath:fastDFS/fdfs_client.conf");
            //上传文件返回文件保存的路径和文件名
            String path = fastDFS.uploadFile(upfile.getBytes(), upfile.getOriginalFilename(), upfile.getSize());
            //拼接上服务器的地址返回给前端
            String url  = file_server_url + path;
            Map<String ,Object > result = new HashMap<>();
            result.put("state","SUCCESS");
            result.put("url",url);
            result.put("title",upfile.getOriginalFilename());
            result.put("original",upfile.getOriginalFilename());
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
