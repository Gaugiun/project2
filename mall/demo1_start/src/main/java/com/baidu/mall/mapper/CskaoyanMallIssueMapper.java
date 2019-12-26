package com.baidu.mall.mapper;

import com.baidu.mall.bean.CskaoyanMallIssue;

public interface CskaoyanMallIssueMapper {
    int deleteByPrimaryKey(Integer id);

    CskaoyanMallIssue selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CskaoyanMallIssue record);

    int updateByPrimaryKey(CskaoyanMallIssue record);
}