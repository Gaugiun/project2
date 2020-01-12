package com.stylefeng.guns.rest.common.persistence.dao;

import com.stylefeng.guns.rest.bean.BaseAreaListVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BaseAreaListMapper {

    List<BaseAreaListVO> select(@Param("areaId") Integer areaId);
}
