package com.baidu.mall.bean;

import lombok.Data;

@Data
public class StatisticsOrderRow {
    private String day;

    private String order;

    private String customers;

    private Integer amount;

    private  Integer pct;

}
