package com.stylefeng.guns.rest.bean.cinema;


import java.io.Serializable;

public class BaseCinemaVO<T> implements Serializable {
    private T data;
    private String imgPre;
    private String msg;
    private Integer nowPage;
    private Integer status;
    private Integer totalPage;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getImgPre() {
        return imgPre;
    }

    public void setImgPre(String imgPre) {
        this.imgPre = imgPre;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getNowPage() {
        return nowPage;
    }

    public void setNowPage(Integer nowPage) {
        this.nowPage = nowPage;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }
}