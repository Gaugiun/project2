package com.stylefeng.guns.rest.common.persistence.dao;

import com.stylefeng.guns.rest.common.persistence.model.MtimeFieldT;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.sql.Timestamp;

/**
 * <p>
 * 放映场次表 Mapper 接口
 * </p>
 *
 * @author qwddfty
 * @since 2020-01-12
 */
public interface MtimeFieldTMapper extends BaseMapper<MtimeFieldT> {

    String selectFieldTimeById(Integer fieldId);
}
