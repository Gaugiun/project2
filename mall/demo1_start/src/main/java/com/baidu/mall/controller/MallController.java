package com.baidu.mall.controller;

import com.baidu.mall.bean.BaseRespVo;
import com.baidu.mall.bean.CskaoyanMallRegion;
import com.baidu.mall.bean.CskaoyanMallUser;
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
}
