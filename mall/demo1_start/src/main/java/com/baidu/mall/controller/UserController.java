package com.baidu.mall.controller;

import com.baidu.mall.bean.BaseRespVo;
import com.baidu.mall.bean.CskaoyanMallUser;
import com.baidu.mall.mapper.CskaoyanMallUserMapper;
import com.baidu.mall.service.UserService;
import com.baidu.mall.service.UserServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
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
    UserService userService;

    @RequestMapping("admin/user/list")
    public BaseRespVo UserList(@RequestBody HashMap map){
        int page = (int) map.get("page");
        int limit = (int) map.get("limit");
        //Object add_time = (int) map.get("add_time");
        //String desc = (int) map.get("desc");

        PageHelper.startPage(page,limit);
        List<CskaoyanMallUser> users = userService.selectAllUser();
        PageInfo<CskaoyanMallUser> userPageInfo = new PageInfo<>(users);

        Map dataMap = new HashMap();
        dataMap.put("total",userPageInfo.getTotal());
        dataMap.put("items",users);

        BaseRespVo resp = new BaseRespVo();
        resp.setData(dataMap);
        resp.setErrno(0);
        resp.setErrmsg("成功");

        return resp;
    }

}
