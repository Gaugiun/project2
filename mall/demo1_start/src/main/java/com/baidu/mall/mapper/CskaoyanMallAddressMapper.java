package com.baidu.mall.mapper;

import com.baidu.mall.bean.CskaoyanMallAddress;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CskaoyanMallAddressMapper {
    int deleteByPrimaryKey(Integer id);

    CskaoyanMallAddress selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CskaoyanMallAddress record);

    int updateByPrimaryKey(CskaoyanMallAddress record);

    List<CskaoyanMallAddress> selectAddress(String name, Integer userId);
}
