package com.baidu.mall.mapper;

import com.baidu.mall.bean.CskaoyanMallFeedback;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CskaoyanMallFeedbackMapper {
    int deleteByPrimaryKey(Integer id);

    CskaoyanMallFeedback selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CskaoyanMallFeedback record);

    int updateByPrimaryKey(CskaoyanMallFeedback record);

    List<CskaoyanMallFeedback> selectFeedback(Integer id, String username);
}
