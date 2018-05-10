package com.fsj.order.exception;

import com.fsj.order.enums.ResultEnum;
import lombok.Data;

/**
 * 统一异常处理
 */
@Data
public class SellException extends RuntimeException {
    private Integer code;
    public SellException(ResultEnum resultEnum){
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }
    public  SellException(int code,String msg){
        super(msg);
        this.code = code;
    }
}
