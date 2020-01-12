package com.stylefeng.guns.rest.modular.auth.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.stylefeng.guns.core.util.MD5Util;
import com.stylefeng.guns.rest.modular.auth.controller.dto.AuthRequest;
import com.stylefeng.guns.rest.modular.auth.controller.dto.AuthResponse;
import com.stylefeng.guns.rest.modular.auth.util.JwtTokenUtil;
import com.stylefeng.guns.rest.modular.vo.ResponseVO;
import com.stylefeng.guns.rest.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

/**
 * 请求验证的
 *
 * @author fengshuonan
 * @Date 2017/8/24 14:22
 */
@RestController
public class AuthController {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

   @Autowired
   private Jedis jedis;

   //@Autowired
   //private IReqValidator dbValidator;

    @Reference(interfaceClass = UserService.class,check = false)
    private UserService userService;


    @RequestMapping(value = "${jwt.auth-path}")
    public ResponseVO createAuthenticationToken(AuthRequest authRequest) {

       //boolean validate = dbValidator.validate(authRequest);
//        // 去掉guns自身携带的用户名密码验证机制，使用自己的
//        int userId = 2;
//        //userService.login(authRequest.getUserName(),authRequest.getPassword());
//        if(userId==0){
//            validate = false;
//        }
        String userName = authRequest.getUserName();
        String password = MD5Util.encrypt(authRequest.getPassword());

        int flag = userService.login(userName, password); //flag表示用户id

        if (flag > 0) {
            //生成token信息并存储到redis中
            final String randomKey = jwtTokenUtil.getRandomKey();
            final String authToken = jwtTokenUtil.generateToken(authRequest.getUserName(), randomKey);
            jedis.set(authToken, String.valueOf(flag));
            jedis.expire(authToken, 1800);
            jedis.set("myToken", authToken);
            jedis.expire("myToken", 1800);
            // 返回值
            return ResponseVO.success(new AuthResponse(authToken, randomKey));
        } else {
            return ResponseVO.serviceFail("用户名或密码错误");
        }
    }
}
