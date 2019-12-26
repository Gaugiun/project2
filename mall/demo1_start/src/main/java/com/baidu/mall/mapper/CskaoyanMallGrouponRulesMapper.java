package com.baidu.mall.mapper;

import com.baidu.mall.bean.CskaoyanMallGrouponRules;

public interface CskaoyanMallGrouponRulesMapper {
    int deleteByPrimaryKey(Integer id);

    CskaoyanMallGrouponRules selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CskaoyanMallGrouponRules record);

    int updateByPrimaryKey(CskaoyanMallGrouponRules record);
}