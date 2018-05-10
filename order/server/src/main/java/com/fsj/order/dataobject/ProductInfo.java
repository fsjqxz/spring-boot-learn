package com.fsj.order.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

//@Table(name = "product_info")  对表不一样就需要这个注释
@Data
@Entity
public class ProductInfo {
    @Id
    private  String productId;
    private String productName;
    private BigDecimal productPrice;
    private Integer productStock;
    private  String productDescription;
    private String productIcon;
    /*0正常，1下架*/
    private  Integer productStatus ;
    /*类目编号*/
    private Integer categoryType;

    private Date createTime;
    private Date updateTime;


}
