package com.fsj.order.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by wxy on 2018-2-5.
 */
@AllArgsConstructor
@Getter
public enum PayStatus {
    WAIT(0, "等待支付"),
    FINISHED(1, "支付成功");


    private Integer code;
    private String msg;

}
