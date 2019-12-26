package com.baidu.mall.controller;

import com.baidu.mall.bean.*;
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

@RequestMapping("admin")
@RestController
public class UserController {

     /**
     * 会员管理  admin/user/list?
      * page: 1
      * limit: 20
      * username: ser  用户名
      * mobile: 188 手机号
      * sort: add_time
      * order: desc
     */
    @Autowired
    UserService userService;

    @RequestMapping("user/list")
    public BaseRespVo UserList(int page,int limit,String username,String mobile){
        PageHelper.startPage(page,limit);
        List<CskaoyanMallUser> users = userService.selectAllUser(username,mobile);
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


    /**
     * 收货地址
     * page: 1
     * limit: 20
     * name: 8  收货人名称
     * userId: 6  用户ID
     * sort: add_time
     * order: desc
     * */
    @RequestMapping("address/list")
    public BaseRespVo AddressList(int page,int limit,String name,Integer userId){
        PageHelper.startPage(page,limit);
        List<CskaoyanMallAddress> addresses = userService.selectAddress(name,userId);
        PageInfo<CskaoyanMallAddress> addressPageInfo = new PageInfo<>(addresses);

        Map dataMap = new HashMap();
        dataMap.put("total",addressPageInfo.getTotal());
        dataMap.put("items",addresses);

        BaseRespVo resp = new BaseRespVo();
        resp.setData(dataMap);
        resp.setErrno(0);
        resp.setErrmsg("成功");

        return resp;
    }

    /**
     * 会员收藏
     * page: 1
     * limit: 20
     * userId: 8   用户ID
     * valueId: 6  商品ID
     * sort: add_time
     * order: desc
     * */
    @RequestMapping("collect/list")
    public BaseRespVo CollectList(int page,int limit,Integer userId,Integer valueId){
        PageHelper.startPage(page,limit);
        List<CskaoyanMallCollect> collects = userService.selectCollect(userId,valueId);
        PageInfo<CskaoyanMallCollect> collectPageInfo = new PageInfo<>(collects);

        Map dataMap = new HashMap();
        dataMap.put("total",collectPageInfo.getTotal());
        dataMap.put("items",collects);

        BaseRespVo resp = new BaseRespVo();
        resp.setData(dataMap);
        resp.setErrno(0);
        resp.setErrmsg("成功");

        return resp;
    }

    /**
     * 会员足迹
     * page: 1
     * limit: 20
     * userId: 1   用户ID
     * goodsId: 2  商品ID
     * sort: add_time
     * order: desc
     * */
    @RequestMapping("footprint/list")
    public BaseRespVo FootprintList(int page,int limit,Integer userId,Integer goodsId){
        PageHelper.startPage(page,limit);
        List<CskaoyanMallFootprint> footprints = userService.selectFootprint(userId,goodsId);
        PageInfo<CskaoyanMallFootprint> footprintPageInfo = new PageInfo<>(footprints);

        Map dataMap = new HashMap();
        dataMap.put("total",footprintPageInfo.getTotal());
        dataMap.put("items",footprints);

        BaseRespVo resp = new BaseRespVo();
        resp.setData(dataMap);
        resp.setErrno(0);
        resp.setErrmsg("成功");

        return resp;
    }

    /**
     * 搜索历史
     * page: 1
     * limit: 20
     * userId: 1   用户ID
     * keyword: 2  关键字
     * sort: add_time
     * order: desc
     * */
    @RequestMapping("history/list")
    public BaseRespVo HistoryList(int page,int limit,Integer userId,String keyword){
        PageHelper.startPage(page,limit);
        List<CskaoyanMallSearchHistory> histories = userService.selectHistory(userId,keyword);
        PageInfo<CskaoyanMallSearchHistory> historyPageInfo = new PageInfo<>(histories);

        Map dataMap = new HashMap();
        dataMap.put("total",historyPageInfo.getTotal());
        dataMap.put("items",histories);

        BaseRespVo resp = new BaseRespVo();
        resp.setData(dataMap);
        resp.setErrno(0);
        resp.setErrmsg("成功");

        return resp;
    }

    /**
     * 意见反馈
     * page: 1
     * limit: 20
     * username: 1   用户名
     * id: 2   反馈Id
     * sort: add_time
     * order: desc
     * */
    @RequestMapping("feedback/list")
    public BaseRespVo FeedbackList(int page,int limit,Integer id,String username){
        PageHelper.startPage(page,limit);
        List<CskaoyanMallFeedback> feedbacks = userService.selectFeedback(id,username);
        PageInfo<CskaoyanMallFeedback> feedbackPageInfo = new PageInfo<>(feedbacks);

        Map dataMap = new HashMap();
        dataMap.put("total",feedbackPageInfo.getTotal());
        dataMap.put("items",feedbacks);

        BaseRespVo resp = new BaseRespVo();
        resp.setData(dataMap);
        resp.setErrno(0);
        resp.setErrmsg("成功");

        return resp;
    }

}
