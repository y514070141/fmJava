package com.fmjava.core.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.fmjava.core.dao.template.TypeTemplateDao;
import com.fmjava.core.pojo.entity.pageResult;
import com.fmjava.core.pojo.good.Brand;
import com.fmjava.core.pojo.template.TypeTemplate;
import com.fmjava.core.pojo.template.TypeTemplateQuery;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class tempLatesServiceImpl implements tempLatesService{

    @Autowired
    private TypeTemplateDao typeTemplateDao;

    @Override
    public pageResult findPage(Integer page, Integer pageSize, TypeTemplate typeTemplate) {
        PageHelper.startPage(page,pageSize);

        TypeTemplateQuery query = new TypeTemplateQuery();
        if(typeTemplate!=null){
            if(typeTemplate.getName()!=null&&!"".equals(typeTemplate.getName())){
                TypeTemplateQuery.Criteria criteria = query.createCriteria();
                criteria.andNameLike("%"+typeTemplate.getName()+"%");
            }
        }
        Page<TypeTemplate> typeTemplatePage = (Page<TypeTemplate>)typeTemplateDao.selectByExample(query);
        pageResult pageResult = new pageResult(typeTemplatePage.getTotal(), typeTemplatePage.getResult());
        System.out.println(1111111111);
        return pageResult;
    }

    @Override
    public void save(TypeTemplate typeTemplate) {
         typeTemplateDao.insertSelective(typeTemplate);
    }

    @Override
    public TypeTemplate findOne(Long id) {
        return typeTemplateDao.selectByPrimaryKey(id);
    }


}
