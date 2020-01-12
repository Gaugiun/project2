package com.stylefeng.guns.rest.common.persistence.dao;

import com.stylefeng.guns.rest.common.persistence.model.BaseUser;
import com.stylefeng.guns.rest.common.persistence.model.MtimeUserT;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author JiYuanbo
 * @since 2020-01-08
 */
public interface MtimeUserTMapper extends BaseMapper<MtimeUserT> {

    BaseUser selectByNameAndPwd(@Param("userName") String userName, @Param("userPwd") String userPwd);

    MtimeUserT selectByName(String username);
}
