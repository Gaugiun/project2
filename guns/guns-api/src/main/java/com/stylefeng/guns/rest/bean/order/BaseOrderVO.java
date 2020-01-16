package com.stylefeng.guns.rest.bean.order;

import lombok.Data;

/**
 * @ClassName BaseOrderVO
 * @Description 用于封装从订单controller返回到试图层的json信息
 * @Author 王唯聪
 * @Data 2020/1/12 20:43
 * @Version 1.0
 **/
@Data
public class BaseOrderVO<T> {
    private T data;

    private String imgPre;

    private String msg;

    private String nowPage;

    private Integer status;

    private String totalPage;
}
