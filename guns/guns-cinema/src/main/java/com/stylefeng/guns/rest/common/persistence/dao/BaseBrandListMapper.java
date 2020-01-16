package com.stylefeng.guns.rest.common.persistence.dao;

import com.stylefeng.guns.rest.bean.BaseBrandListVO;

import java.util.List;

public interface BaseBrandListMapper {

    List<BaseBrandListVO> select(Integer brandId);
}
