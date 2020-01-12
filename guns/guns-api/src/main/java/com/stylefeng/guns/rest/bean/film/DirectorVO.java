package com.stylefeng.guns.rest.bean.film;

import lombok.Data;

import java.io.Serializable;

@Data
public class DirectorVO implements Serializable {
    private static final long serialVersionUID = 5196895387512274117L;
    String directorName;
    String imgAddress;
    String roleName;
}
