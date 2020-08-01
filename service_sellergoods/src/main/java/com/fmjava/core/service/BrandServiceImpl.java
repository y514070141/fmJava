package com.fmjava.core.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.fmjava.core.dao.good.BrandDao;
import com.fmjava.core.pojo.entity.Result;
import com.fmjava.core.pojo.entity.pageResult;
import com.fmjava.core.pojo.good.Brand;
import com.fmjava.core.pojo.good.BrandQuery;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandDao brandDao;

    @Override
    public List<Brand> findAllBrand() {
        return brandDao.selectByExample(null);
    }

    @Override
    public pageResult findPage(Integer page, Integer pageSize,Brand brand) {
        PageHelper.startPage(page, pageSize);

        BrandQuery brandQuery=new BrandQuery();//加一些调价怕
        brandQuery.setOrderByClause("id desc");

        if(brand!=null){//证明是模糊查询
            BrandQuery.Criteria criteria = brandQuery.createCriteria();
            if(brand.getName()!=null&&!"".equals(brand.getName())){
                criteria.andNameLike("%"+brand.getName()+"%");
            }
            if(brand.getFirstChar()!=null&&!"".equals(brand.getFirstChar())){
                criteria.andFirstCharLike("%"+brand.getFirstChar()+"%");
            }
        }

        Page<Brand> brandPage=(Page<Brand>)brandDao.selectByExample(brandQuery);
        pageResult pageResult = new pageResult(brandPage.getTotal(), brandPage.getResult());
        return pageResult;
    }

    @Override
    public Result saveBrand(Brand brand) {
        try{
            brandDao.insertSelective(brand);
            return new Result("保存成功",true);
        }catch (Exception e){
            return new Result("保存失败",false);
        }
    }

    @Override
    public Brand findBrand(Long id) {
        return brandDao.selectByPrimaryKey(id);
    }

    @Override
    public Result updateBrand(Brand brand) {
        try{
            brandDao.updateByPrimaryKeySelective(brand);
            return new Result("修改成功",true);
        }catch (Exception e){
            return new Result("修改失败",false);
        }
    }

    @Override
    public Result deleteBrands(Long[] ids) {
        try{
//            if(ids!=null) {
                //循环遍历删除 方法一
//            for (Long id : ids) {
//                brandDao.deleteByPrimaryKey(id);
//            }
//            }
            //方法二
            if(ids!=null){
                BrandQuery brandQuery = new BrandQuery();
                BrandQuery.Criteria criteria = brandQuery.createCriteria();
                criteria.andIdIn(Arrays.asList(ids));
                brandDao.deleteByExample(brandQuery);
                return new Result("删除成功",true);
            }
            return new Result("请选中一行进行删除",false);
        }catch (Exception e){
            return new Result("删除失败",false);
        }
    }

    @Override
    public List<Map> selectOptionList() {

        return brandDao.selectOptionList();

    }
}
