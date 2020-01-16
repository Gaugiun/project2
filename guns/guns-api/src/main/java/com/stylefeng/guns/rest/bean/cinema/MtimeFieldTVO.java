package com.stylefeng.guns.rest.bean.cinema;

import java.io.Serializable;

/**
 * <p>
 * 放映场次表
 * </p>
 *
 * @author qwddfty
 * @since 2020-01-12
 */
public class MtimeFieldTVO implements Serializable {

    private Integer uuid;

    private Integer cinemaId;

    private Integer filmId;

    private String beginTime;

    private String endTime;

    private Integer hallId;

    private String hallName;

    private Integer price;


    public Integer getUuid() {
        return uuid;
    }

    public void setUuid(Integer uuid) {
        this.uuid = uuid;
    }

    public Integer getCinemaId() {
        return cinemaId;
    }

    public void setCinemaId(Integer cinemaId) {
        this.cinemaId = cinemaId;
    }

    public Integer getFilmId() {
        return filmId;
    }

    public void setFilmId(Integer filmId) {
        this.filmId = filmId;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Integer getHallId() {
        return hallId;
    }

    public void setHallId(Integer hallId) {
        this.hallId = hallId;
    }

    public String getHallName() {
        return hallName;
    }

    public void setHallName(String hallName) {
        this.hallName = hallName;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }


    @Override
    public String toString() {
        return "MtimeFieldTVO{" +
        "uuid=" + uuid +
        ", cinemaId=" + cinemaId +
        ", filmId=" + filmId +
        ", beginTime=" + beginTime +
        ", endTime=" + endTime +
        ", hallId=" + hallId +
        ", hallName=" + hallName +
        ", price=" + price +
        "}";
    }
}
