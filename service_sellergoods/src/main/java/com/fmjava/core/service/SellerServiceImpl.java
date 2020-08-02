package com.fmjava.core.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.fmjava.core.dao.seller.SellerDao;
import com.fmjava.core.pojo.entity.Result;
import com.fmjava.core.pojo.entity.pageResult;
import com.fmjava.core.pojo.seller.Seller;
import com.fmjava.core.pojo.seller.SellerQuery;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.opensaml.xml.signature.Q;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

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

    @Override
    public pageResult findPage(Integer page, Integer pageSize, Seller seller) {
        PageHelper.startPage(page,pageSize);

        SellerQuery Query = new SellerQuery();
        if(seller!=null) {
            SellerQuery.Criteria criteria = Query.createCriteria();
            if(seller.getStatus()!=null&&!"".equals(seller.getStatus())){
                criteria.andStatusEqualTo(seller.getStatus());
            }
            if (seller.getName() != null&&!"".equals(seller.getName())) {
                criteria.andNameLike("%"+seller.getName()+"%");
            }
            if (seller.getNickName() != null&&!"".equals(seller.getNickName())) {
                criteria.andNickNameLike("%"+seller.getNickName()+"%");
            }
        }

        Page<Seller> sellerPage=(Page<Seller>)sellerDao.selectByExample(Query);
        return new pageResult(sellerPage.getTotal(),sellerPage.getResult());
    }

    @Override
    public Result updateStatusById(String sellerId,String status) {
        try{

            Seller seller = new Seller();
            seller.setSellerId(sellerId);
            seller.setStatus(status);
            sellerDao.updateByPrimaryKeySelective(seller);
            return new Result("审核成功",true);
        }catch (Exception e){
            return new Result("审核失败",false);
        }
    }

    @Override
    public Seller selectSellerById(String id) {
        return sellerDao.selectByPrimaryKey(id);
    }
}
