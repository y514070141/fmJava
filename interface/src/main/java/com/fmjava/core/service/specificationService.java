package com.fmjava.core.service;

import com.fmjava.core.pojo.entity.Result;
import com.fmjava.core.pojo.entity.pageResult;
import com.fmjava.core.pojo.entity.specEntity;
import com.fmjava.core.pojo.specification.Specification;

import java.util.List;
import java.util.Map;

public interface specificationService {

    /**
     *
     * @param page 当前页
     * @param pageSize 每页显示
     * @param specification 规格对象
     * @return
     */
    public pageResult findPage(Integer page, Integer pageSize, Specification specification);

    /**
     *
     * @param specEntity
     * @return
     */
    void save(specEntity specEntity);

    /**
     * 编辑回显
     * @param id
     * @return
     */
    specEntity findSpecEntity(Long id);

    /**
     *
     * @param specEntity 修改
     * @return
     */
    void update(specEntity specEntity);

    void deleteSpec(Long[] ids);

    List<Map> selectOneOptionList();

}
