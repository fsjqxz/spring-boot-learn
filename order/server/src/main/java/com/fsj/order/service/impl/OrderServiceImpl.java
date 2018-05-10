package com.fsj.order.service.impl;

import com.fsj.order.dataobject.OrderDetail;
import com.fsj.order.dataobject.OrderMaster;
import com.fsj.order.dto.OrderDTO;
import com.fsj.order.enums.OrderStatus;
import com.fsj.order.enums.PayStatus;
import com.fsj.order.repository.OrderDetailRepository;
import com.fsj.order.repository.OrderMasterRepository;
import com.fsj.order.service.OrderService;
import com.fsj.order.util.KeyUtil;
import com.fsj.product.client.ProductClient;
import com.fsj.product.common.DecreaseStockInput;
import com.fsj.product.common.ProductInfoOutput;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Create  by wyj 1
 * DATE is: 2018-5-9 - 11:08
 * Description:
 */
@Service
public class OrderServiceImpl implements OrderService {


    @Autowired
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    private ProductClient productClient;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Override
    public OrderDTO create(OrderDTO orderDTO) {
        String orderId = KeyUtil.genUniquKey();
        //查询商品信息
        List<String> collect = orderDTO.getOrderDetails().stream()
                .map(e -> e.getProductId()).collect(Collectors.toList());
        List<ProductInfoOutput> list = productClient.findList(collect);
        // 计算总价
        BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);
        for (OrderDetail orderDetail : orderDTO.getOrderDetails()) {
            for (ProductInfoOutput productInfo : list) {
                if (productInfo.getProductId().equals(orderDetail.getProductId()))
                    orderAmount = productInfo.getProductPrice()
                            .multiply(new BigDecimal(orderDetail.getProductQuantity()))
                            .add(orderAmount);
                BeanUtils.copyProperties(productInfo,orderDetail);
                orderDetail.setOrderId(orderId);
                orderDetail.setDetailId(KeyUtil.genUniquKey());
                //订单详情入库
                orderDetailRepository.save(orderDetail);
            }

        }
        // 扣库存
        List<DecreaseStockInput> cartDTOList = orderDTO.getOrderDetails().stream().map(
                e -> new DecreaseStockInput(e.getProductId(),e.getProductQuantity())
        ).collect(Collectors.toList());
        productClient.decreaseStock(cartDTOList);

//        入库
        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setOrderId(orderId);
        BeanUtils.copyProperties(orderDTO, orderMaster);
        orderMaster.setOrderAmount(orderAmount);
        orderMaster.setPayStatus(PayStatus.WAIT.getCode());
        orderMaster.setOrderStatus(OrderStatus.NEW.getCode());
        orderMasterRepository.save(orderMaster);
        return orderDTO;
    }
}
