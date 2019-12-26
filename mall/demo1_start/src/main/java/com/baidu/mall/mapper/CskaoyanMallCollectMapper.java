package com.baidu.mall.mapper;

import com.baidu.mall.bean.CskaoyanMallCollect;

public interface CskaoyanMallCollectMapper {
    int deleteByPrimaryKey(Integer id);

    CskaoyanMallCollect selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CskaoyanMallCollect record);

    int updateByPrimaryKey(CskaoyanMallCollect record);
}