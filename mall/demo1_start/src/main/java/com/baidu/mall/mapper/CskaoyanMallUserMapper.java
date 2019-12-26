package com.baidu.mall.mapper;

import com.baidu.mall.bean.CskaoyanMallUser;

public interface CskaoyanMallUserMapper {
    int deleteByPrimaryKey(Integer id);

    CskaoyanMallUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CskaoyanMallUser record);

    int updateByPrimaryKey(CskaoyanMallUser record);
}