package com.fmjava.core.service;

import com.fmjava.core.pojo.entity.Result;
import com.fmjava.core.pojo.entity.pageResult;
import com.fmjava.core.pojo.good.Brand;

import java.util.List;
import java.util.Map;

public interface BrandService {

    public List<Brand> findAllBrand();


    /**
     *page 当前页
     * pageSize每页数量集合
     */
    public pageResult findPage(Integer page,Integer pageSize,Brand brand);


    /**
     * @param brand 要添加的品牌数据
     * @return
     */
    Result saveBrand(Brand brand);

    /**
     *
     * @param id 通过id查找要修改的brand
     * @return
     */
    Brand findBrand(Long id);

    /**
     *
     * @param brand 修改brand
     * @return
     */
    Result updateBrand(Brand brand);

    /**
     *
     * @param ids 删除的id集合
     * @return
     */
    Result deleteBrands(Long[] ids);


    /**
     *
     * @return
     */
    List<Map> selectOptionList();

}
