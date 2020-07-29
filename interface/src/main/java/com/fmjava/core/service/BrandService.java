package com.fmjava.core.service;

import com.fmjava.core.pojo.entity.pageResult;
import com.fmjava.core.pojo.good.Brand;

import java.util.List;

public interface BrandService {

    public List<Brand> findAllBrand();


    /**
     *page 当前页
     * pageSize每页数量集合
     */
    public pageResult findPage(Integer page,Integer pageSize);

}
