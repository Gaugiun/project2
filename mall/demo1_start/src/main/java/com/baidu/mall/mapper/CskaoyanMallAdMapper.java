package com.baidu.mall.mapper;

import com.baidu.mall.bean.CskaoyanMallAd;

public interface CskaoyanMallAdMapper {
    int deleteByPrimaryKey(Integer id);

    CskaoyanMallAd selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CskaoyanMallAd record);

    int updateByPrimaryKey(CskaoyanMallAd record);
}