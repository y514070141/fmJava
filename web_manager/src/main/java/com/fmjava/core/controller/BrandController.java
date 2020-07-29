package com.fmjava.core.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.fmjava.core.pojo.entity.pageResult;
import com.fmjava.core.pojo.good.Brand;
import com.fmjava.core.service.BrandService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/brand")
public class BrandController{

    @Reference
    private BrandService brandService;

    @RequestMapping("/findAllBrand")
    public List<Brand> brandList(){
        return brandService.findAllBrand();
    }


    @RequestMapping("/findPage")
    public pageResult findPage(Integer page,Integer pageSize){
        pageResult pageResult = brandService.findPage(page, pageSize);
        return pageResult;
    }

}
