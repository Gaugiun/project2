package com.stylefeng.guns.rest.cinema.service.imp;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.stylefeng.guns.rest.bean.BaseAreaListVO;
import com.stylefeng.guns.rest.bean.BaseBrandListVO;
import com.stylefeng.guns.rest.bean.BaseHallTypeListVO;
import com.stylefeng.guns.rest.bean.cinema.BaseCinemaEasyDTO;
import com.stylefeng.guns.rest.bean.cinema.BaseRequestVO;
import com.stylefeng.guns.rest.cinema.service.CinemaService;
import com.stylefeng.guns.rest.common.persistence.dao.*;
import com.stylefeng.guns.rest.common.persistence.model.*;
import com.stylefeng.guns.rest.film.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @ClassName CinemaServiceImp
 * @Description ”影院模块“中根据影院编号，获取影院信息，所有电影的信息和对应的放映场次信息，影院信息
 * @Author 王唯聪
 * @Data 2020/1/8 22:55
 * @Version 1.0
 **/
@Component
@Service
public class CinemaServiceImp implements CinemaService {

    @Autowired
    MtimeCinemaTMapper mtimeCinemaTMapper;

    @Autowired
    MtimeFieldTMapper mtimeFieldTMapper;

    @Autowired
    MtimeHallFilmInfoTMapper mtimeHallFilmInfoTMapper;


    /**
     * @Author 王唯聪
     * @Description
     * @Date 9:04 2020/1/9
     * @Param [cinemaId]
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    @Override
    public Map<String, Object> getField(Integer cinemaId) {
        //从mtime_cinema_t表中查询影院相关信息
        MtimeCinemaT mtimeCinemaT = mtimeCinemaTMapper.selectById(cinemaId);
        Map<String, Object> cinemaInfo = new HashMap<>();
        cinemaInfo.put("cinemaAdress", mtimeCinemaT.getCinemaAddress());
        cinemaInfo.put("cinemaId", mtimeCinemaT.getUuid());
        cinemaInfo.put("cinemaName", mtimeCinemaT.getCinemaName());
        cinemaInfo.put("cinemaPhone", mtimeCinemaT.getCinemaPhone());
        cinemaInfo.put("imgUrl", mtimeCinemaT.getImgAddress());

        //根据hall_ids字符串，到mtime_field_t表中查询该影院的电影厅信息
        /*List<MtimeFieldTVO> mtimeFieldTList = new LinkedList<>();

        String ids = mtimeCinemaT.getHallIds();
        int length = ids.length();
        for (int i=1; i<length; i++) {
            int location = ids.indexOf("#", i);
            String idString = ids.substring(i, location);
            Integer id = Integer.getInteger(idString);
            MtimeFieldTVO mtimeFieldT = mtimeFieldTMapper.selectById(id);
            mtimeFieldTList.add(mtimeFieldT);
        }*/


        ////根据cinemaId参数，到mtime_field_t表中查询对应影院的播放电影信息
        Wrapper<MtimeFieldT> fieldTWrapper = new EntityWrapper<>();
        fieldTWrapper.eq("cinema_id", cinemaId);
        List<MtimeFieldT> mtimeFieldTList =mtimeFieldTMapper.selectList(fieldTWrapper);
        Set<Integer> filmIds = new HashSet<>();
        for (MtimeFieldT mtimeFieldT : mtimeFieldTList) {
            filmIds.add(mtimeFieldT.getFilmId());
        }


        List<Map> filmList = new LinkedList<>();
        for (Integer filmId : filmIds) {
            Wrapper<MtimeHallFilmInfoT> mtimeHallFilmInfoTWrapper = new EntityWrapper<>();
            mtimeHallFilmInfoTWrapper.eq("film_id", filmId);
            List<MtimeHallFilmInfoT> mtimeHallFilmInfoTList = mtimeHallFilmInfoTMapper.selectList(mtimeHallFilmInfoTWrapper);
            for (MtimeHallFilmInfoT mtimeHallFilmInfoT : mtimeHallFilmInfoTList) {
                Map<String, Object> film = new HashMap<>();
                film.put("actors", mtimeHallFilmInfoT.getActors());
                film.put("filmCats", mtimeHallFilmInfoT.getFilmCats());

                Wrapper<MtimeFieldT> mtimeFieldTWrapper = new EntityWrapper<>();
                List filmFields = getFilmFields(mtimeFieldTWrapper, cinemaId, filmId, mtimeHallFilmInfoT.getFilmLanguage());

                film.put("filmFields", filmFields);
                film.put("filmId", mtimeHallFilmInfoT.getFilmId());
                film.put("filmLength", mtimeHallFilmInfoT.getFilmLength());
                film.put("filmName", mtimeHallFilmInfoT.getFilmName());
                film.put("filmType", mtimeHallFilmInfoT.getFilmLanguage());
                film.put("imgAddress", mtimeHallFilmInfoT.getImgAddress());
                filmList.add(film);
            }
        }


        Map<String, Object> map = new HashMap<>();
        map.put("cinemaInfo", cinemaInfo);
        map.put("filmList", filmList);
        return map;
    }


    //用于获取film信息中的filmFields JSON数据
    public List getFilmFields(Wrapper mtimeFieldTWrapper, Integer cinemaId, Integer filmId, String filmLanguage) {
        mtimeFieldTWrapper.eq("cinema_id", cinemaId);
        mtimeFieldTWrapper.eq("film_id", filmId);
        List<MtimeFieldT> mtimeFieldTList = mtimeFieldTMapper.selectList(mtimeFieldTWrapper);
        List<Map> fields = new LinkedList<>();
        for (MtimeFieldT mtimeFieldT : mtimeFieldTList) {
            Map<String, Object> field = new HashMap<>();
            field.put("beginTime", mtimeFieldT.getBeginTime());
            field.put("endTime", mtimeFieldT.getEndTime());
            field.put("fieldId", mtimeFieldT.getUuid());
            field.put("hallName", mtimeFieldT.getHallName());
            field.put("language", filmLanguage);
            field.put("price", mtimeFieldT.getPrice());

            fields.add(field);
        }
        return fields;
    }

    @Autowired
    BaseAreaListMapper baseAreaListMapper;

    @Autowired
    BaseBrandListMapper baseBrandListMapper;

    @Autowired
    BaseHallTypeListMapper baseHallTypeListMapper;


    @Override
    public Map getCondition(Integer brandId, Integer hallType, Integer areaId) {
        List<BaseAreaListVO> areaListVOS = baseAreaListMapper.select(areaId);
        List<BaseBrandListVO> baseBrandListVOS = baseBrandListMapper.select(brandId);
        List<BaseHallTypeListVO> hallTypeListVOS = baseHallTypeListMapper.select(hallType);

        Map resultMap = new HashMap();
        resultMap.put("areaList",areaListVOS);
        resultMap.put("brandList",baseBrandListVOS);
        resultMap.put("halltypeList",hallTypeListVOS);

        return resultMap;
    }

    @Transactional
    @Override
    public List<BaseCinemaEasyDTO> getCinemas(BaseRequestVO requestVO) {
        Integer brandId = requestVO.getBrandId();
        Integer areaId = requestVO.getAreaId();
        Integer hallType = requestVO.getHallType();
        //mtimeCinemaTMapper.selectConditionByBrandidHalltypeAreaid(brandId,hallType,areaId);

        //List<BaseCinemaEasyDTO> cinemaEasyDTOS = mtimeCinemaTMapper.selectConditionByBrandidHalltypeAreaid(requestVO.getBrandId(),requestVO.getHallType(),requestVO.getAreaId());
        return mtimeCinemaTMapper.selectConditionByBrandidHalltypeAreaid(brandId,hallType,areaId);
    }

    @Override
    public String selectCinemaNameById(Integer cinemaId) {
        return mtimeCinemaTMapper.selectCinemaNameById(cinemaId);
    }

}
