package com.fsj.order.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;


/**
 *Create  by  wyj
 *DATE is: 2018-5-9
 *Time is: 10:57
 *Description:
 */


@AllArgsConstructor
@Getter
public enum OrderStatus {
    NEW(0,"新下单"),
    FINISHED(1,"已完结"),
    CANCEL(2,"已取消");

    private Integer code;
    private String msg;
}
