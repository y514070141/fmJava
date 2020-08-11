package com.fmjava.core.service;

import com.fmjava.core.pojo.ad.ContentCategory;
import com.fmjava.core.pojo.entity.pageResult;

public interface contentCategory {
    public pageResult findAll(Integer page, Integer pageSize, ContentCategory contentCategory);
}
