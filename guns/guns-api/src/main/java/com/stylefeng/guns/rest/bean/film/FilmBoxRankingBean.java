package com.stylefeng.guns.rest.bean.film;

import lombok.Data;

import java.io.Serializable;

@Data
public class FilmBoxRankingBean implements Serializable {
    private Integer boxNum;
    private Integer expectNum;
    private String filmCats;
    private String filmId;
    private String filmLength;
    private String filmName;
    private String filmScore;
    private Integer filmType;
    private String imgAddress;
    private String score;
    private String showTime;
}
