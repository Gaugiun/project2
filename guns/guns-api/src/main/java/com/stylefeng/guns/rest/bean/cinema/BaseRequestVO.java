package com.stylefeng.guns.rest.bean.cinema;

import lombok.Data;

import java.io.Serializable;

@Data
public class BaseRequestVO implements Serializable {
    private Integer brandId;
    private Integer hallType;
    private Integer areaId;
    private Integer pageSize;
    private Integer nowPage;
}
