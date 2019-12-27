package com.baidu.mall.service;

import com.baidu.mall.bean.CskaoyanMallBrand;
import com.baidu.mall.bean.CskaoyanMallCategory;
import com.baidu.mall.bean.CskaoyanMallCategoryByLevel;
import com.baidu.mall.bean.CskaoyanMallRegion;
import com.baidu.mall.mapper.CskaoyanMallBrandMapper;
import com.baidu.mall.mapper.CskaoyanMallCategoryMapper;
import com.baidu.mall.mapper.CskaoyanMallRegionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MallServiceImpl implements MallService{
    @Autowired
    CskaoyanMallRegionMapper cskaoyanMallRegionMapper;

    @Transactional
    @Override
    public List<CskaoyanMallRegion> selectRegion() {
        List<CskaoyanMallRegion> regions = selectRegionByPId(0);
        return regions;
    }

    @Autowired
    CskaoyanMallBrandMapper cskaoyanMallBrandMapper;

    @Override
    public List<CskaoyanMallBrand> selectBrand(String name, Integer id) {
        return cskaoyanMallBrandMapper.selectBrand(name,id);
    }

    @Autowired
    CskaoyanMallCategoryMapper cskaoyanMallCategoryMapper;

    @Override
    public List<CskaoyanMallCategory> selectCategory() {
        List<CskaoyanMallCategory> cskaoyanMallCategories = cskaoyanMallCategoryMapper.selectCategoryByPid(0);
        for (CskaoyanMallCategory category : cskaoyanMallCategories){
            Integer pid = category.getId();
            category.setChildren(cskaoyanMallCategoryMapper.selectCategoryByPid(pid));
        }
        return cskaoyanMallCategories;
    }

    @Override
    public List<CskaoyanMallCategoryByLevel> selectCategoryByLevel(int i) {
        return cskaoyanMallCategoryMapper.selectCategoryByLevel(i);
    }

    public List<CskaoyanMallRegion> selectRegionByPId(Integer Pid) {
        List<CskaoyanMallRegion> regions = cskaoyanMallRegionMapper.selectRegionByPId(Pid);
        for(CskaoyanMallRegion region : regions){
            if (region.getType()==3){
                return regions;
            }
            Integer id = region.getId();
            region.setChildren(selectRegionByPId(id));
        }
        return regions;
    }
}
