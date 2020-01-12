package com.stylefeng.guns.rest.bean.film;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class FilmIndexPartBean implements Serializable {
    List<FilmBannerBean> banners;
    List<FilmBoxRankingBean> boxRanking;
    List<FilmBoxRankingBean> expectRanking;
    HotFilm hotFilms;
    HotFilm soonFilms;
    List<FilmBoxRankingBean> top100;

}
