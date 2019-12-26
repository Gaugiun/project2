package com.baidu.mall.bean;

import lombok.Data;

import java.sql.Date;

@Data
public class SystemCharacterItem {
    private Integer id;

    private String name;

    private String desc;

    private boolean enabled;

    private Date addTime;

    private Date updateTime;

    private boolean deleted;
}
