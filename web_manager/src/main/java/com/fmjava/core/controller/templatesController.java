package com.fmjava.core.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.fmjava.core.pojo.entity.Result;
import com.fmjava.core.pojo.entity.pageResult;
import com.fmjava.core.pojo.template.TypeTemplate;
import com.fmjava.core.service.tempLatesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/templates")
public class templatesController {

   @Reference
   private tempLatesService tempLatesService;

    @RequestMapping("/findPage")
    public pageResult findPage(Integer page, Integer pageSize, @RequestBody TypeTemplate typeTemplate){
        return tempLatesService.findPage(page,pageSize,typeTemplate);
    }


    @RequestMapping("/add")
    public Result add(@RequestBody TypeTemplate typeTemplate){
        try{
            tempLatesService.save(typeTemplate);
            return new Result("保存成功",true);
        }catch (Exception e){
            return new Result("保存失败",false);
        }

    }

}
