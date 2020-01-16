package com.stylefeng.guns.rest.cinema.service;


import com.stylefeng.guns.rest.bean.cinema.MtimeFieldTVO;
import com.stylefeng.guns.rest.bean.cinema.MtimeHallDictTVO;

import java.sql.Timestamp;
import java.util.Map;

/**
 * @InterfaceName CinemaFieldInfoService
 * @Description TODO
 * @Author 王唯聪
 * @Data 2020/1/10 15:32
 * @Version 1.0
 **/
public interface CinemaFieldInfoService {
    Map getFieldInfo(Integer cinemaId, Integer fieldId);

    MtimeFieldTVO getFieldsInfo(Integer fieldId);

    MtimeHallDictTVO getHallInfo(Integer hallId);

    String selectFieldTimeById(Integer fieldId);
}
