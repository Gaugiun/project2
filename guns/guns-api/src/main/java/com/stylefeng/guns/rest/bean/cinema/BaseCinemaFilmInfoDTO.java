package com.stylefeng.guns.rest.bean.cinema;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName BaseCinemaFilmInfoDTO
 * @Description TODO
 * @Author 王唯聪
 * @Data 2020/1/10 15:44
 * @Version 1.0
 **/
@Data
public class BaseCinemaFilmInfoDTO implements Serializable {
    private String actors;

    private String filmCats;

    private String filmFields;

    private Integer filmId;

    private String filmLength;

    private String filmName;

    private String filmType;

    private String imgAddress;
}
