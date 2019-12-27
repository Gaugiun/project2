package com.baidu.mall.mapper;

import com.baidu.mall.bean.CskaoyanMallRole;

public interface CskaoyanMallRoleMapper {
    int deleteByPrimaryKey(Integer id);

    CskaoyanMallRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CskaoyanMallRole record);

    int updateByPrimaryKey(CskaoyanMallRole record);
}