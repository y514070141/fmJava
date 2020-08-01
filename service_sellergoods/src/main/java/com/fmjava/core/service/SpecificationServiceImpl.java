package com.fmjava.core.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.fmjava.core.dao.specification.SpecificationDao;
import com.fmjava.core.dao.specification.SpecificationOptionDao;
import com.fmjava.core.pojo.entity.Result;
import com.fmjava.core.pojo.entity.pageResult;
import com.fmjava.core.pojo.entity.specEntity;
import com.fmjava.core.pojo.specification.Specification;
import com.fmjava.core.pojo.specification.SpecificationOption;
import com.fmjava.core.pojo.specification.SpecificationOptionQuery;
import com.fmjava.core.pojo.specification.SpecificationQuery;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class SpecificationServiceImpl implements specificationService{


    @Autowired
    private SpecificationDao specificationDao;

    @Autowired
    private SpecificationOptionDao specificationOptionDao;
    @Override
    public pageResult findPage(Integer page, Integer pageSize, Specification specification) {
        PageHelper.startPage(page,pageSize);
        SpecificationQuery specificationQuery = new SpecificationQuery();

        specificationQuery.setOrderByClause("id desc");

        if(specification.getSpecName()!=null&&!"".equals(specification.getSpecName())){
            SpecificationQuery.Criteria criteria = specificationQuery.createCriteria();
            criteria.andSpecNameLike("%"+specification.getSpecName()+"%");
        }


         Page<Specification> specificationList =(Page<Specification>) specificationDao.selectByExample(specificationQuery);
        pageResult pageResult = new pageResult(specificationList.getTotal(), specificationList.getResult());
        return pageResult;
    }

    @Override
    public void save(specEntity specEntity) {
        //1.增加选项名
        specificationDao.insertSelective(specEntity.getSpec());
        //2.增加选项卡
        for (SpecificationOption specificationOption : specEntity.getSpecOption()) {
            specificationOption.setSpecId(specEntity.getSpec().getId());
            specificationOptionDao.insertSelective(specificationOption);
        }
    }

    @Override
    public specEntity findSpecEntity(Long id) {
        //1.查出规格
        Specification specification = specificationDao.selectByPrimaryKey(id);
        //2.查出选项卡
        SpecificationOptionQuery query = new SpecificationOptionQuery();
        SpecificationOptionQuery.Criteria criteria = query.createCriteria();
        criteria.andSpecIdEqualTo(id);
        List<SpecificationOption> specificationOptions = specificationOptionDao.selectByExample(query);
        //3.封装
        return new specEntity(specification,specificationOptions);
    }

    @Override
    public void update(specEntity specEntity) {
            //1.更新规格
            specificationDao.updateByPrimaryKeySelective(specEntity.getSpec());
            //2.打破关系
            SpecificationOptionQuery query = new SpecificationOptionQuery();
            SpecificationOptionQuery.Criteria criteria = query.createCriteria();
            criteria.andSpecIdEqualTo(specEntity.getSpec().getId());
            specificationOptionDao.deleteByExample(query);
            //3.更新关系
            for (SpecificationOption specificationOption : specEntity.getSpecOption()) {
                specificationOption.setSpecId(specEntity.getSpec().getId());
                specificationOptionDao.insertSelective(specificationOption);
            }
    }

    @Override
    public void deleteSpec(Long[] ids) {
        if(ids!=null){
            for (Long id : ids) {
                //1.删除选项
                specificationDao.deleteByPrimaryKey(id);
                //2.删除选项卡
                SpecificationOptionQuery query = new SpecificationOptionQuery();
                SpecificationOptionQuery.Criteria criteria = query.createCriteria();
                criteria.andSpecIdEqualTo(id);
                specificationOptionDao.deleteByExample(query);
            }

        }

    }

    @Override
    public List<Map> selectOneOptionList() {
        return specificationDao.selectOneOptionList();
    }
}
