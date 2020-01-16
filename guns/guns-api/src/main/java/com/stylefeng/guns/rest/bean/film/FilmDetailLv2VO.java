package com.stylefeng.guns.rest.bean.film;

import lombok.Data;

import java.io.Serializable;

@Data
public class FilmDetailLv2VO implements Serializable {
    private static final long serialVersionUID = 2940802024652403274L;
    private  String filmId;
    private  String filmName;
    private  String filmEnName;
    private  String imgAddress;
    private  String score;
    private  String scoreNum;
    private  String totalBox;
    private  String info01;
    private  String info02;
    private  String info03;
    private FilmInfo04 info04;

}
