package com.stylefeng.guns.rest.cinema.service.imp;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.stylefeng.guns.rest.bean.cinema.*;
import com.stylefeng.guns.rest.cinema.service.CinemaFieldInfoService;
import com.stylefeng.guns.rest.common.persistence.dao.MtimeCinemaTMapper;
import com.stylefeng.guns.rest.common.persistence.dao.MtimeFieldTMapper;
import com.stylefeng.guns.rest.common.persistence.dao.MtimeHallDictTMapper;
import com.stylefeng.guns.rest.common.persistence.dao.MtimeHallFilmInfoTMapper;
import com.stylefeng.guns.rest.common.persistence.model.MtimeCinemaT;
import com.stylefeng.guns.rest.common.persistence.model.MtimeFieldT;
import com.stylefeng.guns.rest.common.persistence.model.MtimeHallDictT;
import com.stylefeng.guns.rest.common.persistence.model.MtimeHallFilmInfoT;
import com.stylefeng.guns.rest.order.service.OrderBuyTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.sql.Timestamp;
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

    @Reference(interfaceClass = OrderBuyTicketService.class, check = false)
    OrderBuyTicketService orderBuyTicketService;


    @Override
    public Map getFieldInfo(Integer cinemaId, Integer fieldId) {
        MtimeCinemaT mtimeCinemaT = mtimeCinemaTMapper.selectById(cinemaId);
        BaseCinemaInfoDTO cinemaInfoDTO = new BaseCinemaInfoDTO();
        if (mtimeCinemaT != null) {
            cinemaInfoDTO.setCinemaAddress(mtimeCinemaT.getCinemaAddress());
            cinemaInfoDTO.setCinemaId(mtimeCinemaT.getUuid());
            cinemaInfoDTO.setCinemaName(mtimeCinemaT.getCinemaName());
            cinemaInfoDTO.setCinemaPhone(mtimeCinemaT.getCinemaPhone());
            cinemaInfoDTO.setImgUrl(mtimeCinemaT.getImgAddress());
        }



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

        String seatSold = orderBuyTicketService.seatSold(fieldId);
        cinemaHallInfoDTO.setSoldSeats(seatSold);//等订单接口写完再写

        Map<String, Object> data = new HashMap<>();
        data.put("cinemaInfo", cinemaInfoDTO);
        data.put("filmInfo", filmInfoDTO);
        data.put("hallInfo", cinemaHallInfoDTO);
        return data;
    }


    @Override
    public MtimeFieldTVO getFieldsInfo(Integer fieldId) {
        MtimeFieldT mtimeFieldT = mtimeFieldTMapper.selectById(fieldId);

        MtimeFieldTVO mtimeFieldTVO = new MtimeFieldTVO();
        mtimeFieldTVO.setUuid(mtimeFieldT.getUuid());
        mtimeFieldTVO.setBeginTime(mtimeFieldT.getBeginTime());
        mtimeFieldTVO.setCinemaId(mtimeFieldT.getCinemaId());
        mtimeFieldTVO.setEndTime(mtimeFieldT.getEndTime());
        mtimeFieldTVO.setFilmId(mtimeFieldT.getFilmId());
        mtimeFieldTVO.setHallId(mtimeFieldT.getHallId());
        mtimeFieldTVO.setHallName(mtimeFieldT.getHallName());
        mtimeFieldTVO.setPrice(mtimeFieldT.getPrice());
        return mtimeFieldTVO;
    }

    @Override
    public MtimeHallDictTVO getHallInfo(Integer hallId) {
        MtimeHallDictT mtimeHallDictT = mtimeHallDictTMapper.selectById(hallId);

        MtimeHallDictTVO mtimeHallDictTVO = new MtimeHallDictTVO();
        mtimeHallDictTVO.setUuid(mtimeHallDictT.getUuid());
        mtimeHallDictTVO.setSeatAddress(mtimeHallDictT.getSeatAddress());
        mtimeHallDictTVO.setShowName(mtimeHallDictT.getShowName());
        return mtimeHallDictTVO;
    }

    @Override
    public String selectFieldTimeById(Integer fieldId) {
        return mtimeFieldTMapper.selectFieldTimeById(fieldId);
    }
}
