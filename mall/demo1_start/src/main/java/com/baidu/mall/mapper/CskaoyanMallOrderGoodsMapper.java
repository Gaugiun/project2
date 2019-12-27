package com.baidu.mall.mapper;

import com.baidu.mall.bean.CskaoyanMallOrderGoods;

public interface CskaoyanMallOrderGoodsMapper {
    int deleteByPrimaryKey(Integer id);

    CskaoyanMallOrderGoods selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CskaoyanMallOrderGoods record);

    int updateByPrimaryKey(CskaoyanMallOrderGoods record);
}