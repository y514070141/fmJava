package com.fmjava.core.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.fmjava.core.pojo.good.Brand;
import com.fmjava.core.pojo.template.TypeTemplate;
import com.fmjava.core.service.BrandService;
import com.fmjava.core.service.tempLatesService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/temp")
public class tempController {

    @Reference
    private tempLatesService tempLatesService;

    @RequestMapping("/findOne")
    public TypeTemplate findOne(Long id){
        return tempLatesService.findOne(id);
    }

}
