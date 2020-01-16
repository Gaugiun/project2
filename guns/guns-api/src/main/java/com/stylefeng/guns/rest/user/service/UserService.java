package com.stylefeng.guns.rest.user.service;

import com.stylefeng.guns.rest.user.vo.UserInfoModel;
import com.stylefeng.guns.rest.user.vo.UserModel;

/**
 * @author Ji Yuanbo
 * @version 1.0
 * @date 2020/1/8 17:49
 */
public interface UserService {

    int login(String username, String pasword);

    boolean register(UserModel userModel);

    boolean checkUsername(String username);

    UserInfoModel getUserInfo(String username);

    UserInfoModel updateUserInfo(UserInfoModel userInfoModel);

}

