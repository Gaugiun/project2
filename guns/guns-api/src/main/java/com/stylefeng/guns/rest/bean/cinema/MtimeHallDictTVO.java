package com.stylefeng.guns.rest.bean.cinema;


import java.io.Serializable;

/**
 * <p>
 * 地域信息表
 * </p>
 *
 * @author qwddfty
 * @since 2020-01-08
 */
public class MtimeHallDictTVO implements Serializable{

    private Integer uuid;

    private String showName;

    private String seatAddress;


    public Integer getUuid() {
        return uuid;
    }

    public void setUuid(Integer uuid) {
        this.uuid = uuid;
    }

    public String getShowName() {
        return showName;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }

    public String getSeatAddress() {
        return seatAddress;
    }

    public void setSeatAddress(String seatAddress) {
        this.seatAddress = seatAddress;
    }


    @Override
    public String toString() {
        return "MtimeHallDictT{" +
        "uuid=" + uuid +
        ", showName=" + showName +
        ", seatAddress=" + seatAddress +
        "}";
    }
}
