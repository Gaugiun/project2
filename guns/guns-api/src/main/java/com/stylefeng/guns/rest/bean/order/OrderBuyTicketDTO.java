package com.stylefeng.guns.rest.bean.order;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName OrderBuyTicketDTO
 * @Description 用于封装返回到response的json数据
 * @Author 王唯聪
 * @Data 2020/1/12 20:57
 * @Version 1.0
 **/
@Data
public class OrderBuyTicketDTO implements Serializable {
    private String cinemaName;

    private String fieldTime;

    private String filmName;

    private String orderId;

    private String orderPrice;

    private String orderStatus;

    private String orderTimestamp;

    private String seatsName;
}
