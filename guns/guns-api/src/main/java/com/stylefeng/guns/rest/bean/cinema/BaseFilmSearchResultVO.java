package com.stylefeng.guns.rest.bean.cinema;

import lombok.Data;

import java.io.Serializable;

@Data
public class BaseFilmSearchResultVO implements Serializable {
    private Integer boxNum;
    private Integer expectNum;
    private String filmCats;
    private String fileId;
    private String filmLength;
    private String filmName;
    private String filemScore;
    private Integer filmType;
    private String imgAddress;
    private String score;
    private String showTime;
}
