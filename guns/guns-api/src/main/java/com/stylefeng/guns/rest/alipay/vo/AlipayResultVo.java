package com.stylefeng.guns.rest.alipay.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Ji Yuanbo
 * @version 1.0
 * @date 2020/1/10 19:24
 */
@Data
public class AlipayResultVo implements Serializable {

    private String orderId;
    private Integer orderStatus;
    private String orderMsg;

}
