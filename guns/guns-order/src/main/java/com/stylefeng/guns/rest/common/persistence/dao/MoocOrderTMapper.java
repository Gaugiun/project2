package com.stylefeng.guns.rest.common.persistence.dao;

import com.stylefeng.guns.rest.alipay.vo.OrderVOR;
import com.stylefeng.guns.rest.common.persistence.model.MoocOrderT;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 订单信息表 Mapper 接口
 * </p>
 *
 * @author qwddfty
 * @since 2020-01-13
 */
public interface MoocOrderTMapper extends BaseMapper<MoocOrderT> {
    MoocOrderT getLast(String uuid);

    OrderVOR getOrderInfoById(@Param("orderId") String orderId);
}
