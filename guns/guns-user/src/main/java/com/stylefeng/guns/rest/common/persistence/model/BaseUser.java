package com.stylefeng.guns.rest.common.persistence.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class BaseUser implements Serializable {

    private static final long serialVersionUID = 2L;

    private Integer uuid;

    private String userName;

    private String userPwd;
}
