package com.baidu.mall.mapper;

import com.baidu.mall.bean.CskaoyanMallGoods;

public interface CskaoyanMallGoodsMapper {
    int deleteByPrimaryKey(Integer id);

    CskaoyanMallGoods selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CskaoyanMallGoods record);

    int updateByPrimaryKeyWithBLOBs(CskaoyanMallGoods record);

    int updateByPrimaryKey(CskaoyanMallGoods record);
}