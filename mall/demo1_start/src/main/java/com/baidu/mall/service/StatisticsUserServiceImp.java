package com.baidu.mall.service;

import com.baidu.mall.bean.CskaoyanMallUser;
import com.baidu.mall.bean.StatisticsUsersRow;
import com.baidu.mall.mapper.CskaoyanMallUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatisticsUserServiceImp implements  StatisticsUsersService {
    @Autowired
    CskaoyanMallUserMapper cskaoyanMallUserMapper;


    /**
     * @return List<CskaoyanMallUser>
     * 为了使用所有user对象中的last_login_time和“每天注册人数”信息，
     */
    @Override
    public List<StatisticsUsersRow> querryDateNumble() {
        List<StatisticsUsersRow> statisticsUsersRowList = cskaoyanMallUserMapper.selectDateNumble();
        System.out.println(statisticsUsersRowList);
        return statisticsUsersRowList;
    }
}
