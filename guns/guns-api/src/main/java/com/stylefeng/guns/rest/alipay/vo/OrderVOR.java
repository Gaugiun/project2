package com.stylefeng.guns.rest.alipay.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class OrderVOR implements Serializable {

    private String orderId;
    private String filmName;
    private String fieldTime;
    private String cinemaName;
    private String seatsName;
    private String orderPrice;
    private String orderTimestamp;
    private String orderStatus;

}
