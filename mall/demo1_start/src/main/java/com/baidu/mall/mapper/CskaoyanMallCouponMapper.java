package com.baidu.mall.mapper;

import com.baidu.mall.bean.CskaoyanMallCoupon;

public interface CskaoyanMallCouponMapper {
    int deleteByPrimaryKey(Integer id);

    CskaoyanMallCoupon selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CskaoyanMallCoupon record);

    int updateByPrimaryKey(CskaoyanMallCoupon record);
}