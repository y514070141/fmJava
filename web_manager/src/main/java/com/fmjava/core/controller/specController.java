package com.fmjava.core.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.fmjava.core.pojo.entity.pageResult;
import com.fmjava.core.pojo.specification.Specification;
import com.fmjava.core.service.specificationService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/spec")
public class specController {

    @Reference
    private specificationService specificationService;

    @RequestMapping("/findPage")
    public pageResult fingPage(Integer page, Integer pageSize, @RequestBody Specification specification){
        pageResult result = specificationService.findPage(page, pageSize, specification);
        return result;
    }
}
