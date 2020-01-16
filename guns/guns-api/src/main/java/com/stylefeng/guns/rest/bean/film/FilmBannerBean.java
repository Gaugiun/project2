package com.stylefeng.guns.rest.bean.film;

import lombok.Data;

import java.io.Serializable;

@Data
public class FilmBannerBean implements Serializable {
    private String bannerAddress;
    private String bannerId;
    private String bannerUrl;
}
