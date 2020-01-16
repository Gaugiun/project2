package com.stylefeng.guns.rest.bean;

import lombok.Data;

import java.io.Serializable;

@Data
public class BaseHallTypeListVO implements Serializable {
    private boolean active;
    private Integer halltypeId;
    private String halltypeName;
}
