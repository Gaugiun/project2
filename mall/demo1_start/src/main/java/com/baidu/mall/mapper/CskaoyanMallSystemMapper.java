package com.baidu.mall.mapper;

import com.baidu.mall.bean.CskaoyanMallSystem;

public interface CskaoyanMallSystemMapper {
    int deleteByPrimaryKey(Integer id);

    CskaoyanMallSystem selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CskaoyanMallSystem record);

    int updateByPrimaryKey(CskaoyanMallSystem record);
}