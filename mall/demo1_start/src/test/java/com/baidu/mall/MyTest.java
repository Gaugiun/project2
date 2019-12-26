package com.baidu.mall;

import com.baidu.mall.bean.CskaoyanMallUser;
import com.baidu.mall.mapper.CskaoyanMallUserMapper;
import com.baidu.mall.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class MyTest {
    @Autowired
    UserService userService;
    @Autowired
    CskaoyanMallUserMapper cskaoyanMallUserMapper;
    @Test
    public void mytest(){
        //List<CskaoyanMallUser> users = cskaoyanMallUserMapper.selectAllUser();
        //List<CskaoyanMallUser> users = userService.selectAllUser(String username,String mobile);
/*        for (CskaoyanMallUser user : users){
            System.out.println(user);
        }*/

    }
}
