package com.stylefeng.guns.rest.modular.film;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@ControllerAdvice("com.stylefeng.guns.rest")
@RestController
public class ExceptionController {

    @ExceptionHandler(Exception.class)
    public HashMap handleException(){
        HashMap hashMap = new HashMap();
        hashMap.put("status", 999);
        hashMap.put("msg", "系统出现异常，请联系管理员");
        return hashMap;
    }
}
