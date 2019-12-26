package com.baidu.mall.mapper;

import com.baidu.mall.bean.CskaoyanMallLog;

public interface CskaoyanMallLogMapper {
    int deleteByPrimaryKey(Integer id);

    CskaoyanMallLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CskaoyanMallLog record);

    int updateByPrimaryKey(CskaoyanMallLog record);
}