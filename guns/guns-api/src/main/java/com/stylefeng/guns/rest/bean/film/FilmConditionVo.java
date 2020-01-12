package com.stylefeng.guns.rest.bean.film;

import lombok.Data;

import java.io.Serializable;

@Data
public class FilmConditionVo implements Serializable {
    private Integer status;
    private FilmConditionPartBean data;
    private String imgPre;
    private String msg;
    private String nowPage;
    private String totalPage;
}
