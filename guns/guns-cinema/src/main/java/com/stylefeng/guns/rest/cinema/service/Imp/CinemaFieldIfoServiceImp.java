package com.stylefeng.guns.rest.cinema.service.Imp;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.stylefeng.guns.rest.bean.cinema.BaseCinemaFilmInfoDTO;
import com.stylefeng.guns.rest.bean.cinema.BaseCinemaHallInfoDTO;
import com.stylefeng.guns.rest.bean.cinema.BaseCinemaInfoDTO;
import com.stylefeng.guns.rest.cinema.service.CinemaFieldInfoService;
import com.stylefeng.guns.rest.common.persistence.dao.MtimeCinemaTMapper;
import com.stylefeng.guns.rest.common.persistence.dao.MtimeFieldTMapper;
import com.stylefeng.guns.rest.common.persistence.dao.MtimeHallDictTMapper;
import com.stylefeng.guns.rest.common.persistence.dao.MtimeHallFilmInfoTMapper;
import com.stylefeng.guns.rest.common.persistence.model.MtimeCinemaT;
import com.stylefeng.guns.rest.common.persistence.model.MtimeFieldT;
import com.stylefeng.guns.rest.common.persistence.model.MtimeHallDictT;
import com.stylefeng.guns.rest.common.persistence.model.MtimeHallFilmInfoT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName CinemaFieldIfoServiceImp
 * @Description TODO
 * @Author 王唯聪
 * @Data 2020/1/10 15:36
 * @Version 1.0
 **/
@Component
@Service
public class CinemaFieldIfoServiceImp implements CinemaFieldInfoService {
    @Autowired
    MtimeCinemaTMapper mtimeCinemaTMapper;

    @Autowired
    MtimeFieldTMapper mtimeFieldTMapper;

    @Autowired
    MtimeHallFilmInfoTMapper mtimeHallFilmInfoTMapper;

    @Autowired
    MtimeHallDictTMapper mtimeHallDictTMapper;


    @Override
    public Map getFieldInfo(Integer cinemaId, Integer fieldId) {
        MtimeCinemaT mtimeCinemaT = mtimeCinemaTMapper.selectById(cinemaId);
        BaseCinemaInfoDTO cinemaInfoDTO = new BaseCinemaInfoDTO();
        cinemaInfoDTO.setCinemaAddress(mtimeCinemaT.getCinemaAddress());
        cinemaInfoDTO.setCinemaId(mtimeCinemaT.getUuid());
        cinemaInfoDTO.setCinemaName(mtimeCinemaT.getCinemaName());
        cinemaInfoDTO.setCinemaPhone(mtimeCinemaT.getCinemaPhone());
        cinemaInfoDTO.setImgUrl(mtimeCinemaT.getImgAddress());


        MtimeFieldT mtimeFieldT = mtimeFieldTMapper.selectById(fieldId);

        Wrapper<MtimeHallFilmInfoT> mtimeFilmIfoWrapper = new EntityWrapper<>();
        mtimeFilmIfoWrapper.eq("film_id", mtimeFieldT.getFilmId());
        List<MtimeHallFilmInfoT> mtimeHallFilmInfoTList = mtimeHallFilmInfoTMapper.selectList(mtimeFilmIfoWrapper);
        BaseCinemaFilmInfoDTO filmInfoDTO = new BaseCinemaFilmInfoDTO();
        for (MtimeHallFilmInfoT mtimeHallFilmInfoT : mtimeHallFilmInfoTList) {
            filmInfoDTO.setActors(mtimeHallFilmInfoT.getActors());
            filmInfoDTO.setFilmCats(mtimeHallFilmInfoT.getFilmCats());
            filmInfoDTO.setFilmFields("");
            filmInfoDTO.setFilmId(mtimeHallFilmInfoT.getFilmId());
            filmInfoDTO.setFilmLength(mtimeHallFilmInfoT.getFilmLength());
            filmInfoDTO.setFilmName(mtimeHallFilmInfoT.getFilmName());
            filmInfoDTO.setFilmType(mtimeHallFilmInfoT.getFilmLanguage());
            filmInfoDTO.setImgAddress(mtimeHallFilmInfoT.getImgAddress());
        }

        BaseCinemaHallInfoDTO cinemaHallInfoDTO = new BaseCinemaHallInfoDTO();
        cinemaHallInfoDTO.setDiscountPrice("");
        cinemaHallInfoDTO.setHallFielId(mtimeFieldT.getHallId());
        cinemaHallInfoDTO.setHallName(mtimeFieldT.getHallName());
        cinemaHallInfoDTO.setPrice(mtimeFieldT.getPrice());

        MtimeHallDictT mtimeHallDictT = mtimeHallDictTMapper.selectById(mtimeFieldT.getHallId());
        cinemaHallInfoDTO.setSeatFile(mtimeHallDictT.getSeatAddress());
        cinemaHallInfoDTO.setSoldSeats(null);//等订单接口写完再写

        Map<String, Object> data = new HashMap<>();
        data.put("cinemaInfo", cinemaInfoDTO);
        data.put("filmInfo", filmInfoDTO);
        data.put("hallInfo", cinemaHallInfoDTO);
        return data;
    }
}
