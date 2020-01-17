package com.stylefeng.guns.rest.order.service.imp;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.rest.bean.cinema.BaseCinemaInfoDTO;
import com.stylefeng.guns.rest.bean.cinema.MtimeCinemaTVO;
import com.stylefeng.guns.rest.bean.cinema.MtimeFieldTVO;
import com.stylefeng.guns.rest.bean.cinema.MtimeHallDictTVO;
import com.stylefeng.guns.rest.bean.film.FilmDetailLv1VO;
import com.stylefeng.guns.rest.bean.order.BaseBuyTicketsVO;
import com.stylefeng.guns.rest.bean.order.OrderBuyTicketDTO;
import com.stylefeng.guns.rest.cinema.service.CinemaFieldInfoService;
import com.stylefeng.guns.rest.cinema.service.CinemaService;
import com.stylefeng.guns.rest.common.persistence.dao.MoocOrderTMapper;
import com.stylefeng.guns.rest.common.persistence.dao.MtimeFieldTMapper;
import com.stylefeng.guns.rest.common.persistence.dao.MtimeHallDictTMapper;
import com.stylefeng.guns.rest.common.persistence.model.MoocOrderT;
import com.stylefeng.guns.rest.common.persistence.model.MtimeFieldT;
import com.stylefeng.guns.rest.common.persistence.model.MtimeHallDictT;
import com.stylefeng.guns.rest.config.RedisConfig;
import com.stylefeng.guns.rest.film.service.FilmService;
import com.stylefeng.guns.rest.modular.auth.util.JwtTokenUtil;
import com.stylefeng.guns.rest.order.service.OrderBuyTicketService;
import com.stylefeng.guns.rest.order.util.Json2String;

import com.stylefeng.guns.rest.user.service.UserService;
import com.stylefeng.guns.rest.user.vo.UserInfoModel;
import lombok.experimental.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.*;

/**
 * @ClassName OrderBuyTicketServiceImp
 * @Description TODO
 * @Author 王唯聪
 * @Data 2020/1/12 21:42
 * @Version 1.0
 **/
@Component
@Service
public class OrderBuyTicketServiceImp implements OrderBuyTicketService {

    @Autowired
    MoocOrderTMapper moocOrderTMapper;

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Autowired
    RedisConfig redisConfig;

    @Reference(interfaceClass = FilmService.class, check = false)
    FilmService filmService;

    @Reference(interfaceClass = CinemaService.class, check = false)
    CinemaService cinemaService;

    @Reference(interfaceClass = CinemaFieldInfoService.class, check = false)
    CinemaFieldInfoService cinemaFieldInfoService;

    @Reference(interfaceClass = UserService.class, check = false)
    UserService userService;

    @Override
    public OrderBuyTicketDTO buyticket(Integer fieldId, String soldSeat, String seatsName) {
        MtimeFieldTVO fieldsInfo = cinemaFieldInfoService.getFieldsInfo(fieldId);

        Boolean isTrueSeat = null;
        try {
           isTrueSeat = isTrueSeat(fieldsInfo.getHallId(), soldSeat);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Boolean isSoldSeat = isSoldSeat(fieldId, soldSeat);
        if (isTrueSeat == true && isSoldSeat == false) {
            MoocOrderT moocOrderT = new MoocOrderT();
            String uuid = UUID.randomUUID().toString().replace("-", "").toLowerCase();
            moocOrderT.setUuid(uuid);
            moocOrderT.setCinemaId(fieldsInfo.getCinemaId());
            moocOrderT.setFieldId(fieldId);
            moocOrderT.setFilmId(fieldsInfo.getFilmId());
            moocOrderT.setSeatsIds(soldSeat);
            moocOrderT.setSeatsName(seatsName);
            moocOrderT.setFilmPrice(fieldsInfo.getPrice().doubleValue());
            String[] soldSeats = soldSeat.split(",");
            int seatNum = soldSeats.length;
            moocOrderT.setOrderPrice(fieldsInfo.getPrice().doubleValue() * seatNum);
            Timestamp date = new Timestamp(System.currentTimeMillis());
            moocOrderT.setOrderTime(date);
            Jedis jedis = redisConfig.jedis();
            Integer userId = Integer.valueOf(jedis.get(jedis.get("myToken")));
            moocOrderT.setOrderUser(userId);    //从token中获取user信息
            moocOrderT.setOrderStatus(0);
            Integer insert = moocOrderTMapper.insert(moocOrderT);
            if (insert != 1) {
                return  null;
            }

            FilmDetailLv1VO filmDeatils = filmService.getFilmDeatils(fieldsInfo.getFilmId().toString());

            OrderBuyTicketDTO orderBuyTicketDTO = new OrderBuyTicketDTO();
            Map map = cinemaFieldInfoService.getFieldInfo(fieldsInfo.getCinemaId(), fieldId);
            BaseCinemaInfoDTO cinemaInfo = (BaseCinemaInfoDTO) map.get("cinemaInfo");

            MoocOrderT last = moocOrderTMapper.getLast(uuid);
            orderBuyTicketDTO.setCinemaName(cinemaInfo.getCinemaName());
            orderBuyTicketDTO.setFieldTime(date.toString());
            orderBuyTicketDTO.setFilmName(filmDeatils.getData().getFilmName());
            orderBuyTicketDTO.setOrderId(last.getUuid());
            orderBuyTicketDTO.setOrderPrice(moocOrderT.getOrderPrice().toString());
            orderBuyTicketDTO.setOrderStatus(last.getOrderStatus().toString());
            orderBuyTicketDTO.setOrderTimestamp(date.toString());
            orderBuyTicketDTO.setSeatsName(last.getSeatsName());

            return orderBuyTicketDTO;
        } else {
            return null;
        }
    }


    /**
     * @Author 王唯聪
     * @Description 验证下前端发送过来的购买作为编号是否为真
     * @Date 9:01 2020/1/13
     * @Param [fieldId, soldSeat]
     * @return java.lang.Boolean
     **/
    private Boolean isTrueSeat(Integer hallId, String soldSeat) throws IOException {
        MtimeHallDictTVO hallInfo = cinemaFieldInfoService.getHallInfo(hallId);
        String seatAddress = hallInfo.getSeatAddress();
        String newSeatAddress = seatAddress.substring(6);
        String seatAddressPre = "E:\\相关资料\\film-front\\static\\seats\\" + newSeatAddress;
        String json = Json2String.json2String(seatAddressPre);
        JSONObject jsonObject = JSONObject.parseObject(json);
        String ids = (String) jsonObject.get("ids");
        if (ids.contains(soldSeat.toString())) {
            return true;
        }
        return false;
    }
    /**
     * @Author 王唯聪
     * @Description 看看购买的座位是不是已经售出。防止出现卖重
     * @Date 9:04 2020/1/13
     * @Param [fieldId, soldSeat]
     * @return java.lang.Boolean
     **/
    private Boolean isSoldSeat(Integer fieldId, String soldSeat) {
        Wrapper<MoocOrderT> moocOrderTWrapper = new EntityWrapper<>();
        moocOrderTWrapper.eq("field_id", fieldId);
        List<MoocOrderT> moocOrderTList = moocOrderTMapper.selectList(moocOrderTWrapper);
        HashSet<String> seatIdsSet = new HashSet<>();
        for (MoocOrderT moocOrderT : moocOrderTList) {
            String seatId = moocOrderT.getSeatsIds();
            String[] split = seatId.split(",");
            for (String id : split) {
                seatIdsSet.add(id);
            }
        }
        if (seatIdsSet.contains(soldSeat)) {
            return true;
        }
        return false;
    }


    @Override
    public String seatSold(Integer fieldId) {
        Wrapper<MoocOrderT> moocOrderTWrapper = new EntityWrapper<>();
        moocOrderTWrapper.eq("field_id", fieldId);
        List<MoocOrderT> moocOrderTList = moocOrderTMapper.selectList(moocOrderTWrapper);
        HashSet<String> seatIdsSet = new HashSet<>();
        for (MoocOrderT moocOrderT : moocOrderTList) {
            String seatId = moocOrderT.getSeatsIds();
            String[] split = seatId.split(",");
            for (String id : split) {
                seatIdsSet.add(id);
            }
        }
        String[] s = seatIdsSet.toArray(new String[seatIdsSet.size()]);
        StringBuilder sb = new StringBuilder();
        for(String str : s){
            sb.append(str).append(",");
        }
        return sb.toString();

    }

    @Override
    public List<BaseBuyTicketsVO> selectOrderList(Integer nowPage, Integer pageSize, Integer userId) {
        Page<MoocOrderT> page = new Page<>(nowPage, pageSize);
        Wrapper wrapper = new EntityWrapper();
        wrapper.eq("order_user",userId);
        wrapper.orderBy("order_time", false);
        List<MoocOrderT> orderTList = moocOrderTMapper.selectPage(page, wrapper);  // 这个查出来的是 List<MoocOrderT>
        List<BaseBuyTicketsVO> resultOrder = new ArrayList<>();

        for (int i = 0; i < orderTList.size(); i++) {
            MoocOrderT moocOrderT = orderTList.get(i);
            resultOrder.add(i,new BaseBuyTicketsVO());
            BaseBuyTicketsVO T = resultOrder.get(i);
            T.setCinemaName(cinemaService.selectCinemaNameById(moocOrderT.getCinemaId()));
            Date date = new Date(System.currentTimeMillis());
            String s = date.toString() + "  ";
            T.setFieldTime(s + cinemaFieldInfoService.selectFieldTimeById(moocOrderT.getFieldId()));
            T.setFilmName(filmService.selectNameById(moocOrderT.getFilmId()));
            T.setOrderId(moocOrderT.getUuid());
            T.setOrderPrice(String.valueOf(moocOrderT.getOrderPrice()));
            Integer orderStatus = moocOrderT.getOrderStatus();
            if (orderStatus==0){
                T.setOrderStatus("未支付");
            } else if (orderStatus==1){
                T.setOrderStatus("已支付");
            }
            Timestamp orderTime = moocOrderT.getOrderTime();
            T.setOrderTimestamp(orderTime.getTime()/1000);
            T.setSeatsName(moocOrderT.getSeatsName());

        }
        return resultOrder;
    }

    @Override
    public Integer selectAllOrderList(Integer nowPage, Integer pageSize, Integer userId) {
        Wrapper<MoocOrderT> wrapper = new EntityWrapper<>();
        wrapper.eq("order_user",userId);
        List<MoocOrderT> moocOrderTS = moocOrderTMapper.selectList(wrapper);
        return moocOrderTS.size();
    }
}
