package com.baidu.mall.service;

import com.baidu.mall.bean.*;
import com.baidu.mall.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    CskaoyanMallUserMapper cskaoyanMallUserMapper;
    @Transactional
    @Override
    public List<CskaoyanMallUser> selectAllUser(String username,String mobile) {
        return cskaoyanMallUserMapper.selectAllUser(username,mobile);
    }

    @Autowired
    CskaoyanMallAddressMapper cskaoyanMallAddressMapper;
    @Transactional
    @Override
    public List<CskaoyanMallAddress> selectAddress(String name, Integer userId) {
        return cskaoyanMallAddressMapper.selectAddress(name,userId);
    }

    @Autowired
    CskaoyanMallCollectMapper cskaoyanMallCollectMapper;
    @Transactional
    @Override
    public List<CskaoyanMallCollect> selectCollect(Integer userId, Integer valueId) {
        return cskaoyanMallCollectMapper.selectCollect(userId,valueId);
    }

    @Autowired
    CskaoyanMallFootprintMapper cskaoyanMallFootprintMapper;
    @Transactional
    @Override
    public List<CskaoyanMallFootprint> selectFootprint(Integer userId, Integer goodsId) {
        return cskaoyanMallFootprintMapper.selectFootprint(userId,goodsId);
    }

    @Autowired
    CskaoyanMallSearchHistoryMapper cskaoyanMallSearchHistoryMapper;
    @Transactional
    @Override
    public List<CskaoyanMallSearchHistory> selectHistory(Integer userId, String keyword) {
        return cskaoyanMallSearchHistoryMapper.selectHistory(userId,keyword);
    }

    @Autowired
    CskaoyanMallFeedbackMapper cskaoyanMallFeedbackMapper;
    @Transactional
    @Override
    public List<CskaoyanMallFeedback> selectFeedback(Integer id, String username) {
        return cskaoyanMallFeedbackMapper.selectFeedback(id,username);
    }
}
