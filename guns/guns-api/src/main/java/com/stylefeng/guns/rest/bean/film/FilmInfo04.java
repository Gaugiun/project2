package com.stylefeng.guns.rest.bean.film;

import lombok.Data;

import java.io.Serializable;

@Data
public class FilmInfo04 implements Serializable {
    private static final long serialVersionUID = 5678099789254071779L;
    ActorAndDirectorVO actors;
    String biopgraphy;
    String filmId;
    ImgVO imgVO;
}
