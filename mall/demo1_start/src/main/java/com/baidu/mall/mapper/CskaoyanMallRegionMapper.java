package com.baidu.mall.mapper;

import com.baidu.mall.bean.CskaoyanMallRegion;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CskaoyanMallRegionMapper {
    int deleteByPrimaryKey(Integer id);

    CskaoyanMallRegion selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CskaoyanMallRegion record);

    int updateByPrimaryKey(CskaoyanMallRegion record);

    List<CskaoyanMallRegion> selectRegionByType(Integer typeId);

    List<CskaoyanMallRegion> selectRegionByPId(Integer pid);
}
