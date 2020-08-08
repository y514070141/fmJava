package com.fmjava.core.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.fmjava.core.pojo.item.ItemCat;
import com.fmjava.core.service.itemCatService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/item_cat")
public class goodsController {

    @Reference
    private itemCatService itemCatService;

    @RequestMapping("/findByParentId")
    public List<ItemCat> findByParentId(Long id){
        return itemCatService.findByParentId(id);
    }

    @RequestMapping("/findOne")
    public ItemCat findOne(Long id){//查找模板
        return itemCatService.findOne(id);
    }

}
