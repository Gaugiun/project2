package com.stylefeng.guns.rest.bean;

import lombok.Data;

import java.io.Serializable;

@Data
public class BaseBrandListVO implements Serializable {
    private boolean active;
    private Integer brandId;
    private String brandName;
}
