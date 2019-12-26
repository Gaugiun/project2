package com.baidu.mall.controller;

import com.baidu.mall.bean.BaseRespVo;
import com.baidu.mall.bean.CskaoyanMallUser;
import com.baidu.mall.mapper.CskaoyanMallUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {

     /**
     * 会员管理  admin/user/list? page=1& limit=20& sort=add_time& order=desc
     */

    @Autowired
     CskaoyanMallUserMapper cskaoyanMallUserMapper;

    @RequestMapping("admin/user/list")
    public BaseRespVo UserList(){

        List<CskaoyanMallUser> users = cskaoyanMallUserMapper.selectAllUser();

        Map dataMap = new HashMap();
        dataMap.put("total",22);
        dataMap.put("items",users);

        BaseRespVo resp = new BaseRespVo();
        resp.setData(dataMap);
        resp.setErrno(0);
        resp.setErrmsg("成功");

        return resp;
    }


}
