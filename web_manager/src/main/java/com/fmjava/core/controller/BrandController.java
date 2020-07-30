package com.fmjava.core.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.fmjava.core.pojo.entity.Result;
import com.fmjava.core.pojo.entity.pageResult;
import com.fmjava.core.pojo.good.Brand;
import com.fmjava.core.service.BrandService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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
    public pageResult findPage(Integer page,Integer pageSize,@RequestBody Brand brand){
        pageResult pageResult = brandService.findPage(page, pageSize,brand);
        return pageResult;
    }

    @RequestMapping(value = "/add")
    public Result addBrand(@RequestBody Brand brand){//发送请求对象封装成json数据
        return brandService.saveBrand(brand);
    }

    @RequestMapping(value = "/findById")
    public Brand findBrand(Long id){//发送请求对象封装成json数据
        return brandService.findBrand(id);
    }

    @RequestMapping(value = "/updateBrand")
    public Result updateBrand(@RequestBody Brand brand){
        return brandService.updateBrand(brand);
    }

    @RequestMapping("/deleteBrands")
    public Result deleteBrands(Long[]ids){
        return brandService.deleteBrands(ids);
    }
}
