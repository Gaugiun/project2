package com.stylefeng.guns.rest.bean.film;

import lombok.Data;

import java.io.Serializable;

@Data
public class FilmRequestInfo implements Serializable {
    private static final long serialVersionUID = 6034959592390536012L;
    private  Integer showType = 1;
    private  Integer sortId=1;
    private  Integer sourceId = 99;
    private  Integer catId = 99;
    private  Integer yearId = 99;
    private  Integer nowPage = 1;
    private  Integer pageSize =18;

}
