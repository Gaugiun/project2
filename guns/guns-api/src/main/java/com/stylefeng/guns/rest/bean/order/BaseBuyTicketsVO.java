package com.stylefeng.guns.rest.bean.order;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
public class BaseBuyTicketsVO implements Serializable {
    private String cinemaName;
    private String fieldTime;
    private String filmName;
    private String orderId;
    private String orderPrice;
    private String orderStatus;
    private Long orderTimestamp;
    private String seatsName;
}
