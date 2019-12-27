package com.baidu.mall.mapper;

import com.baidu.mall.bean.CskaoyanMallAddress;
import com.baidu.mall.bean.CskaoyanMallUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CskaoyanMallUserMapper {
    int deleteByPrimaryKey(Integer id);

    CskaoyanMallUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CskaoyanMallUser record);

    int updateByPrimaryKey(CskaoyanMallUser record);

    List<CskaoyanMallUser> selectAllUser(String username,String mobile);

}
