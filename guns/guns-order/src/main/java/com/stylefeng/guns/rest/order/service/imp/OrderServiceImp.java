package com.stylefeng.guns.rest.order.service.imp;

import com.alibaba.dubbo.config.annotation.Service;
import com.stylefeng.guns.rest.alipay.service.OrderService;
import com.stylefeng.guns.rest.alipay.vo.OrderVOR;
import com.stylefeng.guns.rest.common.persistence.dao.MoocOrderTMapper;
import com.stylefeng.guns.rest.common.persistence.model.MoocOrderT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @ClassName OrderServiceImp
 * @Description TODO
 * @Author Ji Yuanbo
 * @Data 2020/1/14 17:31
 * @Version 1.0
 **/
@Component
@Service
public class OrderServiceImp implements OrderService {
    @Autowired
    MoocOrderTMapper moocOrderTMapper;


    @Override
    public OrderVOR getOrderInfoById(String orderId) {

        OrderVOR orderInfoById = moocOrderTMapper.getOrderInfoById(orderId);

        return orderInfoById;
    }

    @Override
    public boolean paySuccess(String orderId) {
        MoocOrderT orderT = new MoocOrderT();
        orderT.setUuid(orderId);
        orderT.setOrderStatus(1);

        Integer integer = moocOrderTMapper.updateById(orderT);
        if(integer>=1){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean payFail(String orderId) {
        MoocOrderT OrderT = new MoocOrderT();
        OrderT.setUuid(orderId);
        OrderT.setOrderStatus(2);

        Integer integer = moocOrderTMapper.updateById(OrderT);
        if(integer>=1){
            return true;
        }else{
            return false;
        }
    }
}
