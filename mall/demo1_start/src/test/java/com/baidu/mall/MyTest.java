package com.baidu.mall;

import com.baidu.mall.bean.CskaoyanMallRegion;
import com.baidu.mall.bean.CskaoyanMallUser;
import com.baidu.mall.mapper.CskaoyanMallUserMapper;
import com.baidu.mall.service.MallService;
import com.baidu.mall.service.MallServiceImpl;
import com.baidu.mall.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class MyTest {
    @Autowired
    MallServiceImpl mallServiceImpl;
    @Autowired
    CskaoyanMallUserMapper cskaoyanMallUserMapper;
    @Test
    public void mytest(){
/*        List<CskaoyanMallRegion> regions = mallServiceImpl.selectRegion();
        for (CskaoyanMallRegion region:regions){
            System.out.println(region.toString());

        }*/
    }
}
