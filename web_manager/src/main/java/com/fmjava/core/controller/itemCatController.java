package com.fmjava.core.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.fmjava.core.pojo.item.ItemCat;
import com.fmjava.core.service.itemCatService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/itemCat")
public class itemCatController {

    @Reference
    private itemCatService itemCatService;

    @RequestMapping("/findByParentId")
    public List<ItemCat> findByParentId(Long parentId) {
        return itemCatService.findByParentId(parentId);
    }
}
