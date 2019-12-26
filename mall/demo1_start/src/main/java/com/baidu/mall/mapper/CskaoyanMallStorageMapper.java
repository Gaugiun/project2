package com.baidu.mall.mapper;

import com.baidu.mall.bean.CskaoyanMallStorage;

public interface CskaoyanMallStorageMapper {
    int deleteByPrimaryKey(Integer id);

    CskaoyanMallStorage selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CskaoyanMallStorage record);

    int updateByPrimaryKey(CskaoyanMallStorage record);
}