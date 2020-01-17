package com.stylefeng.guns.rest.modular.order;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.rpc.RpcContext;

import com.baomidou.mybatisplus.plugins.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.stylefeng.guns.core.util.ToolUtil;
import com.stylefeng.guns.rest.alipay.service.AlipayService;
import com.stylefeng.guns.rest.alipay.service.OrderService;
import com.stylefeng.guns.rest.alipay.vo.AlipayInfoVo;
import com.stylefeng.guns.rest.alipay.vo.AlipayResultVo;
import com.stylefeng.guns.rest.bean.cinema.BaseCinemaVO;
import com.stylefeng.guns.rest.common.CurrentUser;
import com.stylefeng.guns.rest.config.RedisConfig;
import com.stylefeng.guns.rest.modular.auth.util.JwtTokenUtil;
import com.stylefeng.guns.rest.modular.vo.ResponseVO;
import com.stylefeng.guns.rest.bean.order.BaseBuyTicketsVO;
import com.stylefeng.guns.rest.bean.order.BaseOrderVO;
import com.stylefeng.guns.rest.bean.order.OrderBuyTicketDTO;
import com.stylefeng.guns.rest.order.service.OrderBuyTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

import java.util.List;

/**
 * @ClassName OrderController
 * @Description 订单和支付相关板块
 * @Author 王唯聪
 * @Data 2020/1/12 20:39
 * @Version 1.0
 **/
@RestController
@RequestMapping("order")
public class OrderController {
    @Autowired
    RedisConfig redisConfig;

    @Autowired
    JwtTokenUtil JwtTokenUtil;

    @Reference(interfaceClass = OrderBuyTicketService.class)
    OrderBuyTicketService orderBuyTicketService;


    @RequestMapping("buyTickets")
    public BaseOrderVO<Object> buyTickets(Integer fieldId, String soldSeats, String seatsName) {
        OrderBuyTicketDTO orderBuyTicketDTO = orderBuyTicketService.buyticket(fieldId, soldSeats, seatsName);


        BaseOrderVO<Object> baseOrderVO = new BaseOrderVO<>();
        baseOrderVO.setData(orderBuyTicketDTO);
        baseOrderVO.setImgPre("");
        baseOrderVO.setMsg("");
        baseOrderVO.setNowPage("");
        baseOrderVO.setStatus(0);
        baseOrderVO.setTotalPage("");
        return  baseOrderVO;
    }
    @RequestMapping("getOrderInfo")
    public BaseCinemaVO getOrderInfo(Integer nowPage, Integer pageSize){
        /*PageHelper.startPage(nowPage, pageSize);
        Jedis jedis = redisConfig.jedis();
        Integer userId = Integer.valueOf(jedis.get(jedis.get("myToken")));

        List<BaseBuyTicketsVO> orderList = orderBuyTicketService.selectOrderList(nowPage, pageSize, userId);
        PageInfo<BaseBuyTicketsVO> baseBuyTicketsVOPageInfo = new PageInfo<>(orderList);
        BaseCinemaVO baseCinemaVo = new BaseCinemaVO<>();
        baseCinemaVo.setData(orderList);
        int totalPage = (int) Math.ceil(baseBuyTicketsVOPageInfo.getTotal() / (double)pageSize);
        baseCinemaVo.setTotalPage(totalPage);
        baseCinemaVo.setNowPage(nowPage);*/

        Jedis jedis = redisConfig.jedis();
        Integer userId = Integer.valueOf(jedis.get(jedis.get("myToken")));

        List<BaseBuyTicketsVO> orderList = orderBuyTicketService.selectOrderList(nowPage, pageSize, userId);
        Integer allOrderList = orderBuyTicketService.selectAllOrderList(nowPage, pageSize, userId);
        BaseCinemaVO baseCinemaVo = new BaseCinemaVO<>();
        baseCinemaVo.setData(orderList);
        int totalPage = (int) Math.ceil(allOrderList / (double)pageSize);
        baseCinemaVo.setTotalPage(totalPage);
        baseCinemaVo.setNowPage(nowPage);

        return baseCinemaVo;
    }

    /**
     * @author Ji Yuanbo
     * @version 1.0
     * @date 2020/1/10 19:09
     */

    private static final String IMG_PRE="https://qwddfty-1256376956.cos.ap-beijing.myqcloud.com/";

    @Reference(interfaceClass = AlipayService.class,check = false, timeout = 12000)
    private AlipayService alipayService;


    @RequestMapping(value = "getPayInfo",method = RequestMethod.POST)
    public ResponseVO getPayInfo(@RequestParam("orderId") String orderId){
        // 获取当前登陆人的信息
        Jedis jedis = redisConfig.jedis();
        String usernameFromToken = JwtTokenUtil.getUsernameFromToken(jedis.get("myToken"));
        if(usernameFromToken==null || usernameFromToken.trim().length()==0){
            return ResponseVO.serviceFail("抱歉，用户未登陆");
        }
        // 订单二维码返回结果
        AlipayInfoVo aliPayInfoVo = alipayService.getQRCode(orderId);
        return ResponseVO.success(IMG_PRE, aliPayInfoVo);
    }


    @RequestMapping(value = "getPayResult",method = RequestMethod.POST)
    public ResponseVO getPayResult(
            @RequestParam("orderId") String orderId,
            @RequestParam(name="tryNums",required = false,defaultValue = "1") Integer tryNums) {
        // 获取当前登陆人的信息
        Jedis jedis = redisConfig.jedis();
        String usernameFromToken = JwtTokenUtil.getUsernameFromToken(jedis.get("myToken"));
        if(usernameFromToken==null || usernameFromToken.trim().length()==0){
            return ResponseVO.serviceFail("抱歉，用户未登陆");
        }

        // 将当前登陆人的信息传递给后端
        RpcContext.getContext().setAttachment("userName",usernameFromToken);

        // 判断是否支付超时
        if(tryNums>=4){
            return ResponseVO.serviceFail("订单支付失败，请稍后重试");
        }else{
            AlipayResultVo alipayResultVo = alipayService.getOrderStatus(orderId);
            if(alipayResultVo == null || ToolUtil.isEmpty(alipayResultVo.getOrderId())){
                AlipayResultVo serviceFailVo = new AlipayResultVo();
                serviceFailVo.setOrderId(orderId);
                serviceFailVo.setOrderStatus(0);
                //serviceFailVo.setOrderMsg("支付不成功");
                return ResponseVO.success(serviceFailVo);
            }
            return ResponseVO.success(alipayResultVo);
        }

    }
}
