package com.fmjava.core.service;

import com.fmjava.core.pojo.entity.pageResult;
import com.fmjava.core.pojo.template.TypeTemplate;

public interface tempLatesService {

    /**
     * @param page
     * @param pageSize
     * @param typeTemplate
     * @return
     */
    public pageResult findPage(Integer page, Integer pageSize, TypeTemplate typeTemplate);

    /**
     * 增加模板
     * @param typeTemplate
     * @return
     */
    void save(TypeTemplate typeTemplate);

}
