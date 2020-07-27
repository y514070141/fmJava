package com.fmjava.core.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.fmjava.core.pojo.ad.Content;
import com.fmjava.core.pojo.good.Brand;
import com.fmjava.core.service.TestService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BrandController {

    @Reference
    private TestService testService;

    @RequestMapping("/getName")
    public List<Brand> getName(){
        return testService.getName();
//        return "manager controller";
    }
}
