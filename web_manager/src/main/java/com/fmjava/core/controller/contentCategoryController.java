package com.fmjava.core.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.fmjava.core.pojo.ad.ContentCategory;
import com.fmjava.core.pojo.entity.pageResult;
import com.fmjava.core.service.contentCategory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/contentCategory")
public class contentCategoryController
{
    //注入接口
    @Reference
    private contentCategory contentCategory;

    @RequestMapping("/search")
    public pageResult search(Integer page, Integer pageSize, @RequestBody ContentCategory category){
        return contentCategory.findAll(page,pageSize,category);
    }
}
