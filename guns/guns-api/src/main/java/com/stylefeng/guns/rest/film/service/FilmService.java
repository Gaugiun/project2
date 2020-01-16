package com.stylefeng.guns.rest.film.service;


import com.stylefeng.guns.rest.bean.film.FilmConditionPartBean;
import com.stylefeng.guns.rest.bean.film.FilmDetailLv1VO;
import com.stylefeng.guns.rest.bean.film.FilmGetVo;
import com.stylefeng.guns.rest.bean.film.FilmIndexPartBean;

public interface FilmService {

    FilmIndexPartBean getIndex();

    FilmConditionPartBean getConditionList(Integer cartId, Integer sourceId, Integer yearId);

    FilmGetVo getHotFilms(boolean isLimit, Integer count , Integer showType, Integer sourceId, Integer yearId, Integer  catId);
    //获取影片详细信息的方法
    FilmDetailLv1VO getFilmDeatils(String filmId);

    String getFilmNameById(Integer filmId);

    String selectNameById(Integer filmId);
}
