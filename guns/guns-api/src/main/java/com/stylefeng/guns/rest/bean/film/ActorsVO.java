package com.stylefeng.guns.rest.bean.film;

import lombok.Data;

import java.io.Serializable;

@Data
public class ActorsVO implements Serializable {
    private static final long serialVersionUID = 6279321193520078035L;
    String directorName;
    String imgAddress;
    String roleName;
}
