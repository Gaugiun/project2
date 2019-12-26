package com.baidu.mall.mapper;

import com.baidu.mall.bean.CskaoyanMallUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CskaoyanMallUserMapper {
    int deleteByPrimaryKey(Integer id);

    CskaoyanMallUser selectByPrimaryKey(Integer id);

    List<CskaoyanMallUser> selectAllUser();

    int updateByPrimaryKeySelective(CskaoyanMallUser record);

    int updateByPrimaryKey(CskaoyanMallUser record);
}
