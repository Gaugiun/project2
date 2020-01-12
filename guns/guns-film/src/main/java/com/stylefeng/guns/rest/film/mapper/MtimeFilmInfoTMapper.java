package com.stylefeng.guns.rest.film.mapper;

import com.stylefeng.guns.rest.film.model.MtimeFilmInfoT;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 影片主表 Mapper 接口
 * </p>
 *
 * @author qwddfty
 * @since 2020-01-08
 */
public interface MtimeFilmInfoTMapper extends BaseMapper<MtimeFilmInfoT> {
    List<MtimeFilmInfoT> selectFilmLength(@Param("filmTUuid") Integer filmTUuid);
}
