package com.stylefeng.guns.rest.alipay.service;


import com.stylefeng.guns.rest.alipay.vo.OrderVOR;

public interface OrderService {

    // 根据订单编号获取订单信息
    OrderVOR getOrderInfoById(String orderId);

    boolean paySuccess(String orderId);

    boolean payFail(String orderId);

}
