package com.baidu.mall.mapper;

import com.baidu.mall.bean.CskaoyanMallOrder;

public interface CskaoyanMallOrderMapper {
    int deleteByPrimaryKey(Integer id);

    CskaoyanMallOrder selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CskaoyanMallOrder record);

    int updateByPrimaryKey(CskaoyanMallOrder record);
}