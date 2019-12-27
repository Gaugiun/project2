package com.baidu.mall.controller;

import com.baidu.mall.bean.*;
import com.baidu.mall.service.MallService;
import com.baidu.mall.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("admin")
@RestController
public class MallController {

    /**
     * 行政管理  admin/region/list
     */
    @Autowired
    MallService mallService;

    @RequestMapping("region/list")
    public BaseRespVo UserList(){
        List<CskaoyanMallRegion> regions = mallService.selectRegion();

        BaseRespVo resp = new BaseRespVo();
        resp.setData(regions);
        resp.setErrno(0);
        resp.setErrmsg("成功");

        return resp;
    }

    /**
     * 品牌制造商  admin/brand/list
     * page: 1
     * limit: 20
     * id: 009     品牌商ID
     * name:       品牌商名称
     * sort: add_time
     * order: desc
     */
    @RequestMapping("brand/list")
    public BaseRespVo BrandList(int page,int limit,String name,Integer id){
        PageHelper.startPage(page,limit);
        List<CskaoyanMallBrand> brands = mallService.selectBrand(name,id);
        PageInfo<CskaoyanMallBrand> brandPageInfo = new PageInfo<>(brands);

        Map dataMap = new HashMap();
        dataMap.put("total",brandPageInfo.getTotal());
        dataMap.put("items",brands);

        BaseRespVo resp = new BaseRespVo();
        resp.setData(dataMap);
        resp.setErrno(0);
        resp.setErrmsg("成功");

        return resp;
    }

    /**
     * 商品类目  admin/category/list
     */
    @RequestMapping("category/list")
    public BaseRespVo CategoryList(){
        List<CskaoyanMallCategory> categories = mallService.selectCategory();

        BaseRespVo resp = new BaseRespVo();
        resp.setData(categories);
        resp.setErrno(0);
        resp.setErrmsg("成功");

        return resp;
    }

    @RequestMapping("category/l1")
    public BaseRespVo CategoryL1(){
        List<CskaoyanMallCategoryByLevel> categoriesL1 = mallService.selectCategoryByLevel(1);

        BaseRespVo resp = new BaseRespVo();
        resp.setData(categoriesL1);
        resp.setErrno(0);
        resp.setErrmsg("成功");

        return resp;
    }
}
