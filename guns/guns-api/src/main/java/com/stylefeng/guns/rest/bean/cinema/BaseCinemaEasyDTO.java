package com.stylefeng.guns.rest.bean.cinema;

import lombok.Data;

import java.io.Serializable;

@Data
public class BaseCinemaEasyDTO implements Serializable {
    private String cinemaAddress;
    private String cinemaName;
    private Integer minimumPrice;
    private Integer uuid;
}
