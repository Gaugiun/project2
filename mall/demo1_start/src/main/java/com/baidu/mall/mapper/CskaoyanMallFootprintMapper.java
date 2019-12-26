package com.baidu.mall.mapper;

import com.baidu.mall.bean.CskaoyanMallFootprint;

public interface CskaoyanMallFootprintMapper {
    int deleteByPrimaryKey(Integer id);

    CskaoyanMallFootprint selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CskaoyanMallFootprint record);

    int updateByPrimaryKey(CskaoyanMallFootprint record);
}