package com.baidu.mall.mapper;

import com.baidu.mall.bean.CskaoyanMallFeedback;

public interface CskaoyanMallFeedbackMapper {
    int deleteByPrimaryKey(Integer id);

    CskaoyanMallFeedback selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CskaoyanMallFeedback record);

    int updateByPrimaryKey(CskaoyanMallFeedback record);
}