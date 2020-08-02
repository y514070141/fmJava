package com.fmjava.core.service;

import com.fmjava.core.pojo.entity.Result;
import com.fmjava.core.pojo.entity.pageResult;
import com.fmjava.core.pojo.seller.Seller;

public interface SellerService {
    void add(Seller seller);

    pageResult findPage(Integer page, Integer pageSize, Seller seller);

    Result updateStatusById(String sellerId,String status);

    Seller selectSellerById(String id);
}
