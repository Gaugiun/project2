package com.stylefeng.guns.rest.bean.film;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ActorAndDirectorVO implements Serializable {
    private static final long serialVersionUID = 673585277346139743L;
    List<ActorsVO> actors;
   DirectorVO director;
}
