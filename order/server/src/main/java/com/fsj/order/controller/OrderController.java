package com.fsj.order.controller;

import com.fsj.order.conveter.OrderForm2OrderDTOConverter;
import com.fsj.order.dto.OrderDTO;
import com.fsj.order.enums.ResultEnum;
import com.fsj.order.exception.SellException;
import com.fsj.order.form.OrderForm;
import com.fsj.order.service.OrderService;
import com.fsj.order.util.ResultUtil;
import com.fsj.order.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * Create  by wyj
 * DATE is: 2018-5-9 - 11:07
 * Description:
 */
@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {



    @Autowired
    private OrderService orderService;
    /**
     * 1. 阐述校验
     * 2.查询商品信息
     * 3.计算总价
     * 4.扣库存
     * 5.订单入库
     */
    @PostMapping("create")
    public ResultVO create(@Valid OrderForm orderForm,
                           BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("【创建订单】参数不正确，orderForm={}", orderForm);
            throw new SellException(ResultEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }
        OrderDTO orderDTO = new OrderDTO();
        orderDTO = OrderForm2OrderDTOConverter.convert(orderForm);
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetails())){
            log.error("【创建订单】购物车不能为空");
            throw  new SellException(ResultEnum.CART_EMPTY);
        }
        OrderDTO result = orderService.create(orderDTO);
        Map<String,String> map = new HashMap<>();
        map.put("orderId",result.getOrderId());
        orderService.create(orderDTO);
        return ResultUtil.success(map);
    }

    @GetMapping("/getProductList")
    public String  getProductList(){
 return "";
    }

}
