package com.fmjava.core.service;

import com.fmjava.core.pojo.entity.pageResult;
import com.fmjava.core.pojo.seller.Seller;

public interface SellerService {
    void add(Seller seller);

    pageResult findPage(Integer page, Integer pageSize, Seller seller);
}
