package com.stylefeng.guns.rest.film.mapper;

import com.stylefeng.guns.rest.film.model.MtimeBannerT;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * banner信息表 Mapper 接口
 * </p>
 *
 * @author qwddfty
 * @since 2020-01-08
 */
public interface MtimeBannerTMapper extends BaseMapper<MtimeBannerT> {

    List<MtimeBannerT> select();
}
