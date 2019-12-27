package com.baidu.mall.mapper;

import com.baidu.mall.bean.CskaoyanMallComment;

public interface CskaoyanMallCommentMapper {
    int deleteByPrimaryKey(Integer id);

    CskaoyanMallComment selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CskaoyanMallComment record);

    int updateByPrimaryKey(CskaoyanMallComment record);
}