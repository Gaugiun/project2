package com.stylefeng.guns.rest.bean.film;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class HotFilm implements Serializable {
    List<FilmBoxRankingBean> filmInfo;
    private Integer filmNum;
    private String nowPage;
    private String totalPage;

}
