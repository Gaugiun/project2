package com.baidu.mall.mapper;

import com.baidu.mall.bean.CskaoyanMallSearchHistory;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CskaoyanMallSearchHistoryMapper {
    int deleteByPrimaryKey(Integer id);

    CskaoyanMallSearchHistory selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CskaoyanMallSearchHistory record);

    int updateByPrimaryKey(CskaoyanMallSearchHistory record);

    List<CskaoyanMallSearchHistory> selectHistory(Integer userId, String keyword);
}
