package com.fmjava.core.service;

import com.fmjava.core.pojo.item.ItemCat;

import java.util.List;

public interface itemCatService {

    public List<ItemCat> findByParentId(Long parentId);

    //模板
    ItemCat findOne(Long id);
}
