package com.stylefeng.guns.rest.common.persistence.dao;

import com.stylefeng.guns.rest.bean.BaseHallTypeListVO;

import java.util.List;

public interface BaseHallTypeListMapper {

    List<BaseHallTypeListVO> select(Integer hallType);
}
