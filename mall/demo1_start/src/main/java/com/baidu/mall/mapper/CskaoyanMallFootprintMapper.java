package com.baidu.mall.mapper;

import com.baidu.mall.bean.CskaoyanMallFootprint;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CskaoyanMallFootprintMapper {
    int deleteByPrimaryKey(Integer id);

    CskaoyanMallFootprint selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CskaoyanMallFootprint record);

    int updateByPrimaryKey(CskaoyanMallFootprint record);

    List<CskaoyanMallFootprint> selectFootprint(Integer userId, Integer goodsId);
}
