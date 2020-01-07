package com.stylefeng.guns.rest.cinema.service.Imp;

import com.alibaba.dubbo.config.annotation.Service;
import com.stylefeng.guns.rest.cinema.service.CinemaService;
import org.springframework.stereotype.Component;

@Component
@Service(interfaceClass = CinemaService.class)
public class CinemaServiceImp implements CinemaService {
    public String mark() {
        return "成功";
    }
}
