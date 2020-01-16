package com.stylefeng.guns.rest.bean.film;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class FilmConditionPartBean implements Serializable {
    List<FilmConditionCatInfoBean> catInfo;
    List<FilmConditionSourceBean> sourceInfo;
    List<FilmConditionYearBean> yearInfo;


}
