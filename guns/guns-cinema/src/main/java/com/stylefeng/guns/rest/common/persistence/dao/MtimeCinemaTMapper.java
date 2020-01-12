package com.stylefeng.guns.rest.common.persistence.dao;

import com.stylefeng.guns.rest.bean.cinema.BaseCinemaEasyDTO;
import com.stylefeng.guns.rest.common.persistence.model.MtimeCinemaT;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 影院信息表 Mapper 接口
 * </p>
 *
 * @author qwddfty
 * @since 2020-01-08
 */
@Mapper
public interface MtimeCinemaTMapper extends BaseMapper<MtimeCinemaT> {
    List<BaseCinemaEasyDTO> selectConditionByBrandidHalltypeAreaid(Integer brandId, Integer hallType, Integer areaId);
}
