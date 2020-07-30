package com.fmjava.core.service;

import com.fmjava.core.pojo.entity.pageResult;
import com.fmjava.core.pojo.specification.Specification;

public interface specificationService {

    /**
     *
     * @param page 当前页
     * @param pageSize 每页显示
     * @param specification 规格对象
     * @return
     */
    public pageResult findPage(Integer page, Integer pageSize, Specification specification);

}
