package com.stylefeng.guns.rest.bean.cinema;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName BaseCinemaInfoDTO
 * @Description TODO
 * @Author 王唯聪
 * @Data 2020/1/10 15:41
 * @Version 1.0
 **/
@Data
public class BaseCinemaInfoDTO implements Serializable {
    private String cinemaAddress;

    private Integer cinemaId;

    private String cinemaName;

    private String cinemaPhone;

    private String imgUrl;
}
