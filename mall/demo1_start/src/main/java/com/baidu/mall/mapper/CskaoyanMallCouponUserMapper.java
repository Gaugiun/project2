package com.baidu.mall.mapper;

import com.baidu.mall.bean.CskaoyanMallCouponUser;

public interface CskaoyanMallCouponUserMapper {
    int deleteByPrimaryKey(Integer id);

    CskaoyanMallCouponUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CskaoyanMallCouponUser record);

    int updateByPrimaryKey(CskaoyanMallCouponUser record);
}