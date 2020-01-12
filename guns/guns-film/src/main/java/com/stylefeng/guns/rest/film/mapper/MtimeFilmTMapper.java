package com.stylefeng.guns.rest.film.mapper;

import com.stylefeng.guns.rest.film.model.MtimeFilmT;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 影片主表 Mapper 接口
 * </p>
 *
 * @author qwddfty
 * @since 2020-01-08
 */
public interface MtimeFilmTMapper extends BaseMapper<MtimeFilmT> {

    ArrayList<MtimeFilmT> selectOrderByFilmBoxOffice();

    ArrayList<MtimeFilmT> selectOrderByFilmPresalenum();

    ArrayList<MtimeFilmT> selectOrderByTop();

    ArrayList<MtimeFilmT> selectOrderByFilmStatus(@Param("status") Integer status);

    List<MtimeFilmT> selectFilms(@Param("showType") Integer showType, @Param("sourceId") Integer sourceId, @Param("yearId") Integer yearId, @Param("catId") String catId);

    String selelctImgAddress(@Param("showType") Integer showType, @Param("sourceId") Integer sourceId, @Param("yearId") Integer yearId, @Param("catId") String catId);
}
