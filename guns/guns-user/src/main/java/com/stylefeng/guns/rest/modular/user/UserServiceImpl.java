package com.stylefeng.guns.rest.modular.user;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.stylefeng.guns.core.util.MD5Util;
import com.stylefeng.guns.rest.common.persistence.dao.MtimeUserTMapper;
import com.stylefeng.guns.rest.common.persistence.model.BaseUser;
import com.stylefeng.guns.rest.common.persistence.model.MtimeUserT;
import com.stylefeng.guns.rest.user.service.UserService;
import com.stylefeng.guns.rest.user.vo.UserInfoModel;
import com.stylefeng.guns.rest.user.vo.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

/**
 * @author Ji Yuanbo
 * @version 1.0
 * @date 2020/1/8 17:48
 */

@Component
@Service(interfaceClass = UserService.class)
public class UserServiceImpl implements UserService {

    @Autowired
    private MtimeUserTMapper mtimeUserTMapper;

    @Override
    public boolean register(UserModel userModel) {
        // 将注册信息实体转换为数据实体
        MtimeUserT mtimeUserT = new MtimeUserT();
        mtimeUserT.setUserName(userModel.getUsername());

        mtimeUserT.setEmail(userModel.getEmail());
        mtimeUserT.setAddress(userModel.getAddress());
        mtimeUserT.setUserPhone(userModel.getMobile());
        // 创建时间和修改时间 -> current_timestamp

        // 数据加密 【MD5混淆加密 + 盐值 -> Shiro加密】
        String md5Password = MD5Util.encrypt(userModel.getPassword());
        mtimeUserT.setUserPwd(md5Password);

        // 将数据实体存入数据库
        Integer insert = mtimeUserTMapper.insert(mtimeUserT);
        if(insert>0) {
            return true;
        }else {
            return false;
        }

    }

    @Override
    public int login(String username, String password) {
        /*// 根据登陆账号获取数据库信息
        MtimeUserT mtimeUserT = new MtimeUserT();
        mtimeUserT.setUserName(username);

        MtimeUserT result = mtimeUserTMapper.selectOne(mtimeUserT);

        // 获取到的结果，然后与加密以后的密码做匹配
        if(result != null && result.getUuid() > 0) {
            String md5Password = MD5Util.encrypt(password);
            if(result.getUserPwd().equals(md5Password)) {
                return result.getUuid();
            }*/
        //EntityWrapper<MtimeUserT> entityWrapper = new EntityWrapper<>();
        //entityWrapper.eq("user_name", username);
        //entityWrapper.eq("user_pwd", MD5Util.encrypt(password));
        //List<MtimeUserT> userList = mtimeUserTMapper.selectList(entityWrapper);
        BaseUser mtimeUserT = mtimeUserTMapper.selectByNameAndPwd(username, password);
        if (mtimeUserT.getUuid() != null) {
            return mtimeUserT.getUuid();
        }
        return 0;
    }

    @Override
    public boolean checkUsername(String username) {
        EntityWrapper<MtimeUserT> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("user_name", username);
        Integer result = mtimeUserTMapper.selectCount(entityWrapper);
        if(result != null && result > 0) {
            return false;
        }else {
            return true;
        }
    }

    private UserInfoModel do2UserInfo(MtimeUserT mtimeUserT) {
        UserInfoModel userInfoModel = new UserInfoModel();

        userInfoModel.setUuid(mtimeUserT.getUuid());
        userInfoModel.setHeadAddress(mtimeUserT.getHeadUrl());
        userInfoModel.setPhone(mtimeUserT.getUserPhone());
        userInfoModel.setUpdateTime(mtimeUserT.getUpdateTime().getTime());
        userInfoModel.setEmail(mtimeUserT.getEmail());
        userInfoModel.setUsername(mtimeUserT.getUserName());
        userInfoModel.setNickname(mtimeUserT.getNickName());
        userInfoModel.setLifeState(""+mtimeUserT.getLifeState());
        userInfoModel.setBirthday(mtimeUserT.getBirthday());
        userInfoModel.setAddress(mtimeUserT.getAddress());
        userInfoModel.setSex(mtimeUserT.getUserSex());
        userInfoModel.setBeginTime(mtimeUserT.getBeginTime().getTime());
        userInfoModel.setBiography(mtimeUserT.getBiography());

        return userInfoModel;

    }


    @Override
    public UserInfoModel getUserInfo(String username) {
        MtimeUserT mtimeUserT = mtimeUserTMapper.selectByName(username);
        UserInfoModel userInfoModel = do2UserInfo(mtimeUserT);
        return userInfoModel;
    }


    /*private Date time2Date(long time) {
        Date date = new Date(time);
        return date;
        time2Date(userInfoModel.getBeginTime())
        time2Date(System.currentTimeMillis())
    }*/

    @Override
    public UserInfoModel updateUserInfo(UserInfoModel userInfoModel) {
        MtimeUserT mtimeUserT = new MtimeUserT();

        mtimeUserT.setUuid(userInfoModel.getUuid());
        mtimeUserT.setNickName(userInfoModel.getNickname());
        mtimeUserT.setLifeState(Integer.parseInt(userInfoModel.getLifeState()));
        mtimeUserT.setBirthday(userInfoModel.getBirthday());
        mtimeUserT.setBiography(userInfoModel.getBiography());
        mtimeUserT.setBeginTime(null);
        mtimeUserT.setHeadUrl(userInfoModel.getHeadAddress());
        mtimeUserT.setEmail(userInfoModel.getEmail());
        mtimeUserT.setAddress(userInfoModel.getAddress());
        mtimeUserT.setUserPhone(userInfoModel.getPhone());
        mtimeUserT.setUserSex(userInfoModel.getSex());
        mtimeUserT.setUpdateTime(null);

        Integer isSuccess = mtimeUserTMapper.updateById(mtimeUserT);
        if(isSuccess > 0) {
            //UserInfoModel userInfo = getUserInfo(mtimeUserT.getUuid());
            UserInfoModel userInfo = getUserInfo(mtimeUserT.getUserName());
            return userInfo;
        }else {
            return userInfoModel;
        }
    }
}
