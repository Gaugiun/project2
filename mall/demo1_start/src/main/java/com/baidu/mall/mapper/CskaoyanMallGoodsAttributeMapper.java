package com.baidu.mall.mapper;

import com.baidu.mall.bean.CskaoyanMallGoodsAttribute;

public interface CskaoyanMallGoodsAttributeMapper {
    int deleteByPrimaryKey(Integer id);

    CskaoyanMallGoodsAttribute selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CskaoyanMallGoodsAttribute record);

    int updateByPrimaryKey(CskaoyanMallGoodsAttribute record);
}