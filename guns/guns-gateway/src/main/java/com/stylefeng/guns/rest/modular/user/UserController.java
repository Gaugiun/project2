package com.stylefeng.guns.rest.modular.user;

import com.alibaba.dubbo.config.annotation.Reference;
import com.stylefeng.guns.rest.common.CurrentUser;
import com.stylefeng.guns.rest.modular.vo.ResponseVO;
import com.stylefeng.guns.rest.user.service.UserService;
import com.stylefeng.guns.rest.user.vo.UserInfoModel;
import com.stylefeng.guns.rest.user.vo.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

/**
 * @author Ji Yuanbo
 * @version 1.0
 * @date 2020/1/8 20:57
 */
@RequestMapping("/user/")
@RestController
public class UserController {

    @Reference(interfaceClass = UserService.class)
    private UserService userService;

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    Jedis jedis;

    @RequestMapping(value="register",method = RequestMethod.POST)
    public ResponseVO register(UserModel userModel){
        if(userModel.getUsername() == null || userModel.getUsername().trim().length()==0){
            return ResponseVO.serviceFail("用户名不能为空");
        }
        if(userModel.getPassword() == null || userModel.getPassword().trim().length()==0){
            return ResponseVO.serviceFail("密码不能为空");
        }

        boolean isSuccess = userService.register(userModel);
        if(isSuccess){
            return ResponseVO.success("注册成功");
        }else{
            return ResponseVO.serviceFail("注册失败");
        }
    }

    @RequestMapping(value="check",method = RequestMethod.POST)
    public ResponseVO check(String username){
        if(username != null && username.trim().length() > 0) {
            boolean notExists = userService.checkUsername(username);
            if (notExists){
                return ResponseVO.success("用户名不存在");
            }else{
                return ResponseVO.serviceFail("用户名已存在");
            }
        }else {
            return ResponseVO.serviceFail("用户名不能为空");
        }
    }

    @RequestMapping(value="logout",method = RequestMethod.GET)
    public ResponseVO logout(){
        //Map myToken = redisTemplate.opsForHash().entries("myToken");
        //redisTemplate.opsForHash().delete(myToken.get("myToken"));
        String myToken = jedis.get("myToken");
        if (myToken != null) {
            jedis.del(myToken);
            jedis.del("myToken");
        }
        return ResponseVO.success("用户退出成功");
    }

    @RequestMapping(value="getUserInfo",method = RequestMethod.GET)
    public ResponseVO getUserInfo(){
        //String userId = CurrentUser.getCurrentUser();
        //if(userId != null && userId.trim().length() > 0) {
            //int uuid = Integer.parseInt(userId);
        String username = CurrentUser.getCurrentUser();
        if(username != null && username.trim().length() > 0) {
            UserInfoModel userInfo = userService.getUserInfo(username);
            if(userInfo != null) {
                return ResponseVO.success(userInfo);
            }else {
                return ResponseVO.appFail("用户信息查询失败");
            }
        }else {
            return ResponseVO.serviceFail("用户未登录");
        }
    }

    @RequestMapping(value="updateUserInfo",method = RequestMethod.POST)
    public ResponseVO updateUserInfo(UserInfoModel userInfoModel){
        /*String userId = CurrentUser.getCurrentUser();
        if(userId != null && userId.trim().length()>0){
            int uuid = Integer.parseInt(userId);
            if(uuid != userInfoModel.getUuid()){
                return ResponseVO.serviceFail("请修改您个人的信息");
            }*/
        String username = CurrentUser.getCurrentUser();
        if(username != null && username.trim().length()>0){
            //int uuid = Integer.parseInt(userId);
            /*if(username != userInfoModel.getUsername()){
                return ResponseVO.serviceFail("请修改您个人的信息");
            }*/
            UserInfoModel userInfo = userService.updateUserInfo(userInfoModel);
            if(userInfo!=null){
                return ResponseVO.success(userInfo);
            }else{
                return ResponseVO.appFail("用户信息修改失败");
            }
        }else{
            return ResponseVO.serviceFail("用户未登陆");
        }
    }



}
