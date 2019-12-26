package com.baidu.mall.bean;

import lombok.Data;

@Data
public class BaseRespVo<T> {
    int errno;
    T data;
    String errmsg;
}
