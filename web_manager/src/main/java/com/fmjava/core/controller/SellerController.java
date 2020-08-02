package com.fmjava.core.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.fmjava.core.pojo.entity.pageResult;
import com.fmjava.core.pojo.seller.Seller;
import com.fmjava.core.service.SellerService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/seller")
public class SellerController {

    @Reference
    private SellerService sellerService;

    @RequestMapping("/findPage")
    public pageResult findPage(Integer page, Integer pageSize,@RequestBody Seller seller){
        return sellerService.findPage(page,pageSize,seller);
    }

}
