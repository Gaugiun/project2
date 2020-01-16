package com.stylefeng.guns.rest.order.service;

import com.stylefeng.guns.rest.bean.order .BaseBuyTicketsVO;
import com.stylefeng.guns.rest.bean.order.OrderBuyTicketDTO;

import java.util.List;

/**
 * @InterfaceName OrderBuyTicketService
 * @Description 订单模块中的卖票Service接口
 * @Author 王唯聪
 * @Data 2020/1/12 20:54
 * @Version 1.0
 **/
public interface OrderBuyTicketService {
    public OrderBuyTicketDTO buyticket(Integer fieldId, String soldSeat, String seatsName);

    public String seatSold(Integer fieldId);

    List<BaseBuyTicketsVO> selectOrderList(Integer nowPage, Integer pageSize, Integer userId);
}
