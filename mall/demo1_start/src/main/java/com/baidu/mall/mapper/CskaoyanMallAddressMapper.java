package com.baidu.mall.mapper;

import com.baidu.mall.bean.CskaoyanMallAddress;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CskaoyanMallAddressMapper {
    int deleteByPrimaryKey(Integer id);

    CskaoyanMallAddress selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CskaoyanMallAddress record);

    int updateByPrimaryKey(CskaoyanMallAddress record);
}