package com.baidu.mall.service;

import com.baidu.mall.bean.CskaoyanMallUser;
import com.baidu.mall.mapper.CskaoyanMallUserMapper;
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
    public List<CskaoyanMallUser> selectAllUser() {
        return cskaoyanMallUserMapper.selectAllUser();
    }
}
