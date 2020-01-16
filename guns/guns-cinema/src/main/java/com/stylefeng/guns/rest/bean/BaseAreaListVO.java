package com.stylefeng.guns.rest.bean;

import lombok.Data;

import java.io.Serializable;

@Data
public class BaseAreaListVO implements Serializable {
    private boolean active = false;
    private Integer areaId;
    private String areaName;
}
