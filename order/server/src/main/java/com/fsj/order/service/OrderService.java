package com.fsj.order.service;

import com.fsj.order.dto.OrderDTO;

/**
 * Create  by wyj
 * DATE is: 2018-5-9 - 11:08
 * Description:
 */

public interface OrderService {
    /**
     * 创建订单
     * @param orderDTO
     * @return
     */
    OrderDTO  create(OrderDTO orderDTO);
}
