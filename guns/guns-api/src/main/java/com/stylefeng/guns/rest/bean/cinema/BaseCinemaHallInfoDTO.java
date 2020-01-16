package com.stylefeng.guns.rest.bean.cinema;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName BaseCinemaHallInfoDTO
 * @Description TODO
 * @Author 王唯聪
 * @Data 2020/1/10 15:47
 * @Version 1.0
 **/
@Data
public class BaseCinemaHallInfoDTO implements Serializable {
    private String discountPrice;

    private Integer hallFielId;

    private String hallName;

    private Integer price;

    private String seatFile;

    private String soldSeats;
}
