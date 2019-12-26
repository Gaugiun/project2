package com.baidu.mall.mapper;

import com.baidu.mall.bean.CskaoyanMallKeyword;

public interface CskaoyanMallKeywordMapper {
    int deleteByPrimaryKey(Integer id);

    CskaoyanMallKeyword selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CskaoyanMallKeyword record);

    int updateByPrimaryKey(CskaoyanMallKeyword record);
}