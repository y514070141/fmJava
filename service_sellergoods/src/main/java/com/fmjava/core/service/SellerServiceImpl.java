package com.fmjava.core.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.fmjava.core.dao.seller.SellerDao;
import com.fmjava.core.pojo.seller.Seller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@Service
@Transactional
public class SellerServiceImpl implements SellerService{

    @Autowired
    private SellerDao sellerDao;

    @Override
    public void add(Seller seller) {
        seller.setCreateTime(new Date());
        seller.setStatus("0");//审核状态
        sellerDao.insertSelective(seller);
    }
}
