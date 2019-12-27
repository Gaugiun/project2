package com.baidu.mall.mapper;

import com.baidu.mall.bean.CskaoyanMallCategory;
import com.baidu.mall.bean.CskaoyanMallCategoryByLevel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CskaoyanMallCategoryMapper {
    int deleteByPrimaryKey(Integer id);

    CskaoyanMallCategory selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CskaoyanMallCategory record);

    int updateByPrimaryKey(CskaoyanMallCategory record);

    List<CskaoyanMallCategory> selectCategoryByPid(Integer pid);

    List<CskaoyanMallCategoryByLevel> selectCategoryByLevel(int i);
}
