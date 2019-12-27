package com.baidu.mall.mapper;

import com.baidu.mall.bean.CskaoyanMallGoodsSpecification;

public interface CskaoyanMallGoodsSpecificationMapper {
    int deleteByPrimaryKey(Integer id);

    CskaoyanMallGoodsSpecification selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CskaoyanMallGoodsSpecification record);

    int updateByPrimaryKey(CskaoyanMallGoodsSpecification record);
}