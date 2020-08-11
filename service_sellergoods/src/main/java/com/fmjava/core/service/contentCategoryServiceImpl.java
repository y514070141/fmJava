package com.fmjava.core.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.fmjava.core.dao.ad.ContentCategoryDao;
import com.fmjava.core.pojo.ad.ContentCategory;
import com.fmjava.core.pojo.ad.ContentCategoryQuery;
import com.fmjava.core.pojo.entity.pageResult;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class contentCategoryServiceImpl implements contentCategory {

    @Autowired
    private ContentCategoryDao contentCategoryDao;

    @Override
    public pageResult findAll(Integer page, Integer pageSize, ContentCategory contentCategory) {
        PageHelper.startPage(page,pageSize);

        ContentCategoryQuery query = new ContentCategoryQuery();
        if(contentCategory!=null||!"".equals(contentCategory)) {
            ContentCategoryQuery.Criteria criteria = query.createCriteria();
            if(contentCategory.getName()!=null&!"".equals(contentCategory.getName()))
            criteria.andNameLike("%"+contentCategory.getName()+"%");
        }

        //查询的集合 封装成Page里面有 总数 页容
        //一个分页 需要 总条数 总页数 页容      总条数/页容=总页数
        Page<ContentCategory> categoryPage =(Page<ContentCategory>) contentCategoryDao.selectByExample(query);
        return new pageResult(categoryPage.getTotal(),categoryPage.getResult());
    }
}
