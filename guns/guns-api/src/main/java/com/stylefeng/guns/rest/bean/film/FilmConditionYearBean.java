package com.stylefeng.guns.rest.bean.film;

import lombok.Data;

import java.io.Serializable;

@Data
public class FilmConditionYearBean implements Serializable {
    private boolean isactive;
    private String yearId;
    private String yearName;
}
