package com.fsj.order.controller;

import com.fsj.product.client.ProductClient;
import com.fsj.order.dto.CartDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Create  by wyj
 * DATE is: 2018-5-9 - 13:41
 * Description:
 */
@RestController
@Slf4j
public class ClientController {

    @Autowired
    private ProductClient productClient;


    //public String  getProductMsg(){
        //第一种方式 (直接使用restTemplate，url写死）
//        RestTemplate restTemplate = new RestTemplate();
//        String forObject = restTemplate.getForObject("http://localhost/msg", String.class);
//        log.info(forObject);

        //第二种方式（利用loadBalancerClient通过应用名获取url，然后写restTemplate)
//       RestTemplate restTemplate = new RestTemplate();
//       ServiceInstance serviceInstance = loadBalancerClient.choose("PRODUCT");
//       String.format("http://%s:%s",serviceInstance.getHost(),serviceInstance.getPort() +"/msg");
//        String forObject = restTemplate.getForObject("http://localhost/msg", String.class);

        //第三种方式（利用@LoadBalanced，可在restTemplate中使用应用名字）
      // String forObject = restTemplate.getForObject("http://PRODUCT/msg", String.class);


  //  }

 /*   @GetMapping("/getProductList")
    public String  getProductList(){
        List<com.fsj.product.common.ProductInfoOutput> list = productClient.findList(Arrays.asList("123456", "1234567"));
        log.info(String.valueOf(list));
       return "ok";
    }


    @GetMapping("/productDecreaseStock")
    public String  decreaseStock(){
        CartDTO cartDTO = new CartDTO("123456",10);
        List<CartDTO> cartDTOS = new ArrayList<>();
        cartDTOS.add(cartDTO);

        productClient.decreaseStock(cartDTOS);
        return "ok";
    }*/
}
