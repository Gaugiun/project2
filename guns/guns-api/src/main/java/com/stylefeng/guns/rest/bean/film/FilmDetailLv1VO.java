package com.stylefeng.guns.rest.bean.film;

import lombok.Data;

import java.io.Serializable;

@Data
public class FilmDetailLv1VO implements Serializable {
    private static final long serialVersionUID = -2888762111873593720L;
    FilmDetailLv2VO data;
    String imgPre;
    String msg;
    String nowPage;
    Integer status;
    String totalPage;
}
