package com.fmjava.core.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.fmjava.core.dao.ad.ContentDao;
import com.fmjava.core.dao.good.BrandDao;
import com.fmjava.core.pojo.ad.Content;
import com.fmjava.core.pojo.good.Brand;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class BrandService implements TestService {

    @Autowired
    private BrandDao brandDao;

    @Override
    public List<Brand> getName() {
        //传输数据 必须实现序列化接口
        List<Brand> brands = brandDao.selectByExample(null);
        return brands;
//        return "TestServiceImpl";
    }
}
