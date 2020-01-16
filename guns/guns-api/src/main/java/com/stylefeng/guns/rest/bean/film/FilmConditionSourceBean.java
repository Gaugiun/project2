package com.stylefeng.guns.rest.bean.film;

import lombok.Data;

import java.io.Serializable;

@Data
public class FilmConditionSourceBean implements Serializable {
    private boolean isactive;
    private String sourceId;
    private String sourceName;
}
