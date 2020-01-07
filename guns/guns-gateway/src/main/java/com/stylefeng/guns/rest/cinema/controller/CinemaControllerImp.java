package com.stylefeng.guns.rest.cinema.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.stylefeng.guns.rest.cinema.service.CinemaService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student")
public class CinemaControllerImp {
    @Reference(interfaceClass = CinemaService.class)
    CinemaService cinemaService;

    @RequestMapping("getName")
    public String getNameById(String id) {

        String result = "连接" + cinemaService.mark();

        return result;
    }
}
