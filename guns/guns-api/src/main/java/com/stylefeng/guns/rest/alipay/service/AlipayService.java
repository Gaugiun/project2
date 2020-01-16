package com.stylefeng.guns.rest.alipay.service;

import com.stylefeng.guns.rest.alipay.vo.AlipayInfoVo;
import com.stylefeng.guns.rest.alipay.vo.AlipayResultVo;

/**
 * @author Ji Yuanbo
 * @version 1.0
 * @date 2020/1/10 19:19
 */
public interface AlipayService {

    AlipayInfoVo getQRCode(String orderId);

    AlipayResultVo getOrderStatus(String orderId);
}
