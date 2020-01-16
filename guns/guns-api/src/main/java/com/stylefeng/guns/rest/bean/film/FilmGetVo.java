package com.stylefeng.guns.rest.bean.film;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class FilmGetVo implements Serializable {
    private static final long serialVersionUID = -7170786185967210669L;
    String imgPre;
    String msg;
    Integer nowPage;
    Integer status;
    Integer totalPage;
    List<FilmBoxRankingBean> data;
}
