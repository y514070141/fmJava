package com.fmjava.core.service;

import com.alibaba.dubbo.config.annotation.Service;

@Service
public class BrandService implements TestService {
    @Override
    public String getName() {
        return "TestServiceImpl";
    }
}
