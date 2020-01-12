package com.stylefeng.guns.rest.bean.film;

import lombok.Data;

import java.io.Serializable;

@Data
public class ImgVO implements Serializable {
    private static final long serialVersionUID = -1410959028187687062L;
    String img01;
    String img02;
    String img03;
    String img04;
    String mainImg;
}
