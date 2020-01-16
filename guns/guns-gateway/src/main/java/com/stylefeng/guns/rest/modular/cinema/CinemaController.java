package com.stylefeng.guns.rest.modular.cinema;

import com.alibaba.dubbo.config.annotation.Reference;
import com.stylefeng.guns.rest.bean.cinema.BaseCinemaEasyDTO;
import com.stylefeng.guns.rest.bean.cinema.BaseCinemaVO;
import com.stylefeng.guns.rest.bean.cinema.BaseRequestVO;
import com.stylefeng.guns.rest.cinema.service.CinemaFieldInfoService;
import com.stylefeng.guns.rest.cinema.service.CinemaService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @ClassName CinemaController
 * @Description 获取影院相关功能
 * @Author 王唯聪
 * @Data 2020/1/8 22:23
 * @Version 1.0
 **/
@RestController
@RequestMapping("/cinema")
public class CinemaController {

    @Reference(interfaceClass = CinemaService.class)
    CinemaService cinemaService;

    @Reference(interfaceClass = CinemaFieldInfoService.class)
    CinemaFieldInfoService cinemaFieldInfoService;

    /**
     * @Author 王唯聪
     * @Description “影院模块”中点击某个影院
     * @Date 22:42 2020/1/8
     * @Param [cinemaId]
     * @return com.stylefeng.guns.rest.bean.cinema.BaseCinemaVO
     **/
    @RequestMapping("getFields")
    public BaseCinemaVO getFields(Integer cinemaId) {
        Map<String, Object> data = cinemaService.getField(cinemaId);

        BaseCinemaVO<Object> baseCinemaVO = new BaseCinemaVO<>();
        baseCinemaVO.setData(data);
        baseCinemaVO.setImgPre("http://img.meetingshop.cn/");
        baseCinemaVO.setMsg("");
        baseCinemaVO.setNowPage(null);
        baseCinemaVO.setStatus(0);
        baseCinemaVO.setTotalPage(null);
        return baseCinemaVO;
    }


    /**
     * @Author 王唯聪
     * @Description 点击“选座购票”按钮，进入到某一影城某一场次电影的详细信息
     * @Date 15:29 2020/1/10
     * @Param [cinemaId]
     * @return com.stylefeng.guns.rest.bean.cinema.BaseCinemaVO
     **/
    @RequestMapping("getFieldInfo")
    public BaseCinemaVO getFieldInfo(Integer cinemaId, Integer fieldId) {
        Map<String, Object> data = cinemaFieldInfoService.getFieldInfo(cinemaId, fieldId);

        BaseCinemaVO<Object> baseCinemaVO = new BaseCinemaVO<>();
        baseCinemaVO.setData(data);
        baseCinemaVO.setImgPre("http://img.meetingshop.cn/");
        baseCinemaVO.setMsg("");
        baseCinemaVO.setNowPage(null);
        baseCinemaVO.setStatus(0);
        baseCinemaVO.setTotalPage(null);
        return baseCinemaVO;
    }

    @RequestMapping("getCondition")
    public BaseCinemaVO getCondition(BaseRequestVO requestVO){
        Map dataMap = cinemaService.getCondition(requestVO.getBrandId(), requestVO.getHallType(), requestVO.getAreaId());
        BaseCinemaVO baseCinemaVo = new BaseCinemaVO<>();
        baseCinemaVo.setData(dataMap);
        baseCinemaVo.setStatus(0);

        return baseCinemaVo;
    }

    @RequestMapping("getCinemas")
    public BaseCinemaVO getCinemas(BaseRequestVO requestVO){  // brandID hallType areaID pageSize nowPage
        List<BaseCinemaEasyDTO> cinemas = cinemaService.getCinemas(requestVO);

        BaseCinemaVO baseCinemaVo = new BaseCinemaVO<>();
        baseCinemaVo.setData(cinemas);
        baseCinemaVo.setImgPre("http://img.meetingshop.cn/");
        baseCinemaVo.setNowPage(1);
        baseCinemaVo.setTotalPage(1);

        return baseCinemaVo;
    }

}
