package com.baidu.mall.mapper;

import com.baidu.mall.bean.CskaoyanMallUserFormid;

public interface CskaoyanMallUserFormidMapper {
    int deleteByPrimaryKey(Integer id);

    CskaoyanMallUserFormid selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CskaoyanMallUserFormid record);

    int updateByPrimaryKey(CskaoyanMallUserFormid record);
}