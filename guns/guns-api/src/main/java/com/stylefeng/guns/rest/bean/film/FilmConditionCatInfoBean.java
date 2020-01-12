package com.stylefeng.guns.rest.bean.film;

import lombok.Data;

import java.io.Serializable;

@Data
public class FilmConditionCatInfoBean implements Serializable {
    private boolean isactive;
    private String catId;
    private String catName;
}
