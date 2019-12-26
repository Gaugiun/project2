package com.baidu.mall.mapper;

import com.baidu.mall.bean.CskaoyanMallCart;

public interface CskaoyanMallCartMapper {
    int deleteByPrimaryKey(Integer id);

    CskaoyanMallCart selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CskaoyanMallCart record);

    int updateByPrimaryKey(CskaoyanMallCart record);
}