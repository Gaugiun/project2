package com.baidu.mall.controller;

import com.baidu.mall.controllerbean.BaseRespVo;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;

@RestController
@RequestMapping("admin")
public class AdminController {
    /**
     * 登录后台管理页面的API
     * @param map
     * @return
     */
    @RequestMapping("auth/login")
    public BaseRespVo login(@RequestBody HashMap<String, String> map){

        BaseRespVo<Object> baseRespVo = new BaseRespVo<>();
        //没有使用shiro的情况下 ，返回任意的这个值就行了
        String username = map.get("username");
        String password = map.get("password");
        if ("admin123".equals(username) && "admin123".equals(password)) {

            baseRespVo.setErrno(0);
            baseRespVo.setData("abc");
            baseRespVo.setErrmsg("成功");
            return baseRespVo;
        }
        return baseRespVo;
    }

    @RequestMapping("auth/info")
    public BaseRespVo info(){
        BaseRespVo baseRespVo = new BaseRespVo();
        baseRespVo.setErrno(0);
        baseRespVo.setErrmsg("成功");
        HashMap<String, Object> map = new HashMap<>();
        map.put("name","admin123");
        map.put("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        ArrayList<String> roleList = new ArrayList<>();
        roleList.add("超级管理员");
        ArrayList<String> permList = new ArrayList<>();
        permList.add("*");

        map.put("roles",roleList);
        map.put("perms",permList);
        baseRespVo.setData(map);
        return baseRespVo;
    }


/*    @Autowired
    UserService userService;

    @RequestMapping("user/list")
    public List<User> userList(int pageNum,int pageSize){
        List<User> users = userService.queryUsers(pageNum, pageSize);
        return users;
    }*/
}
