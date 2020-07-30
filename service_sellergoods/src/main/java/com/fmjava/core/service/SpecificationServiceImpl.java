package com.fmjava.core.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.fmjava.core.dao.specification.SpecificationDao;
import com.fmjava.core.pojo.entity.pageResult;
import com.fmjava.core.pojo.specification.Specification;
import com.fmjava.core.pojo.specification.SpecificationQuery;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SpecificationServiceImpl implements specificationService{


    @Autowired
    private SpecificationDao specificationDao;

    @Override
    public pageResult findPage(Integer page, Integer pageSize, Specification specification) {
        PageHelper.startPage(page,pageSize);
        SpecificationQuery specificationQuery = new SpecificationQuery();

        if(specification.getSpecName()!=null&&!"".equals(specification.getSpecName())){
            SpecificationQuery.Criteria criteria = specificationQuery.createCriteria();
            criteria.andSpecNameLike("%"+specification.getSpecName()+"%");
        }

         Page<Specification> specificationList =(Page<Specification>) specificationDao.selectByExample(specificationQuery);
        pageResult pageResult = new pageResult(specificationList.getTotal(), specificationList.getResult());
        return pageResult;
    }
}
