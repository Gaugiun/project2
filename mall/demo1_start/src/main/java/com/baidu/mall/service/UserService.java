package com.baidu.mall.service;

import com.baidu.mall.bean.*;

import java.util.List;

public interface UserService {

    List<CskaoyanMallUser> selectAllUser(String username,String mobile);

    List<CskaoyanMallAddress> selectAddress(String name, Integer userId);

    List<CskaoyanMallCollect> selectCollect(Integer userId, Integer valueId);

    List<CskaoyanMallFootprint> selectFootprint(Integer userId, Integer goodsId);

    List<CskaoyanMallSearchHistory> selectHistory(Integer userId, String keyword);

    List<CskaoyanMallFeedback> selectFeedback(Integer id, String username);
}
