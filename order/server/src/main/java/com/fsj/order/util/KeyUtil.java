package com.fsj.order.util;

import java.util.Random;

public class KeyUtil {

    /**
     * 生成唯一的主键
     * 格式：时间+随机数
     * synchronized是处理多线程并发的
     * @return
     */
    public static  synchronized String genUniquKey(){
        Random random = new Random();
        Integer number = random.nextInt(900000)+100000;
        return System.currentTimeMillis() + String.valueOf(number);
    }
}
