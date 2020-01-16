package com.stylefeng.guns.rest.bean.cinema;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName BasefilmVO
 * @Description TODO
 * @Author 王唯聪
 * @Data 2020/1/8 21:08
 * @Version 1.0
 **/
@Data
public class BasefilmVO implements Serializable {
    private String actors;

    private String filmCats;

    private List<BaseFilmFieldVO> filmFields;

    private Integer filmId;

    private String filmLength;

    private String filmName;

    private String filmType;

    private String imgAddress;

}
