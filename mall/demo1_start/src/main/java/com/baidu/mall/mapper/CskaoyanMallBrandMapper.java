package com.baidu.mall.mapper;

import com.baidu.mall.bean.CskaoyanMallBrand;

public interface CskaoyanMallBrandMapper {
    int deleteByPrimaryKey(Integer id);

    CskaoyanMallBrand selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CskaoyanMallBrand record);

    int updateByPrimaryKey(CskaoyanMallBrand record);
}