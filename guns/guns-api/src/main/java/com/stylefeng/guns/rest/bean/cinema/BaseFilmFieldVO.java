package com.stylefeng.guns.rest.bean.cinema;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName BaseFilmFieldVO
 * @Description TODO
 * @Author 王唯聪
 * @Data 2020/1/8 21:09
 * @Version 1.0
 **/
@Data
public class BaseFilmFieldVO implements Serializable {
    private String beginTime;

    private String endTime;

    private Integer fieldId;

    private String hallName;

    private String language;

    private String price;

}
