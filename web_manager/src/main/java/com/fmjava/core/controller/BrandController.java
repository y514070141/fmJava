package com.fmjava.core.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.fmjava.core.service.TestService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BrandController {

    @Reference
    private TestService testService;

    @RequestMapping("/getName")
    public String getName(){
        return testService.getName();
//        return "manager controller";
    }
}
