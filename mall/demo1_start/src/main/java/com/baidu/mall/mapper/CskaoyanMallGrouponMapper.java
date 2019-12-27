package com.baidu.mall.mapper;

import com.baidu.mall.bean.CskaoyanMallGroupon;

public interface CskaoyanMallGrouponMapper {
    int deleteByPrimaryKey(Integer id);

    CskaoyanMallGroupon selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CskaoyanMallGroupon record);

    int updateByPrimaryKey(CskaoyanMallGroupon record);
}