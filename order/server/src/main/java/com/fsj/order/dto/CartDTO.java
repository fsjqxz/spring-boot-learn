package com.fsj.order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class CartDTO {


    /*商品id*/
    private String productId;
    /*商品数量*/
    private Integer productQuantity;
}
