package com.baidu.mall.service;

import com.baidu.mall.bean.CskaoyanMallBrand;
import com.baidu.mall.bean.CskaoyanMallCategory;
import com.baidu.mall.bean.CskaoyanMallCategoryByLevel;
import com.baidu.mall.bean.CskaoyanMallRegion;

import java.util.List;

public interface MallService {
    List<CskaoyanMallRegion> selectRegion();

    List<CskaoyanMallBrand> selectBrand(String name, Integer id);

    List<CskaoyanMallCategory> selectCategory();

    List<CskaoyanMallCategoryByLevel> selectCategoryByLevel(int i);
}
