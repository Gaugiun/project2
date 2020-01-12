package com.stylefeng.guns.rest.bean.film;

import lombok.Data;

import java.io.Serializable;

@Data
public class FilmIndexVo implements Serializable {
    private FilmIndexPartBean data;
    private String imgPre;
    private String msg;
    private String nowPage;
    private Integer status;
    private String totalPage;
}
