package com.baidu.mall.service;

import com.baidu.mall.bean.CskaoyanMallRegion;
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
