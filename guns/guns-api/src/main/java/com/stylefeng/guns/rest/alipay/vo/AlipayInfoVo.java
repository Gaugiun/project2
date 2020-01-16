package com.stylefeng.guns.rest.alipay.vo;


import lombok.Data;

import java.io.Serializable;

/**
 * @author Ji Yuanbo
 * @version 1.0
 * @date 2020/1/10 19:20
 */

@Data
public class AlipayInfoVo implements Serializable {

    private String orderId;
    private String QRCodeAddress;
}
