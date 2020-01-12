package com.stylefeng.guns.rest.cinema.service;

import com.stylefeng.guns.rest.bean.cinema.BaseCinemaEasyDTO;
import com.stylefeng.guns.rest.bean.cinema.BaseRequestVO;

import java.util.List;
import java.util.Map;

public interface CinemaService {
    Map<String, Object> getField(Integer cinemaId);

    Map getCondition(Integer brandId, Integer hallType, Integer areaId);

    List<BaseCinemaEasyDTO> getCinemas(BaseRequestVO requestVO);
}
