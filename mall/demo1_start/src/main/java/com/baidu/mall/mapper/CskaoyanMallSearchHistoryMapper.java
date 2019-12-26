package com.baidu.mall.mapper;

import com.baidu.mall.bean.CskaoyanMallSearchHistory;

public interface CskaoyanMallSearchHistoryMapper {
    int deleteByPrimaryKey(Integer id);

    CskaoyanMallSearchHistory selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CskaoyanMallSearchHistory record);

    int updateByPrimaryKey(CskaoyanMallSearchHistory record);
}