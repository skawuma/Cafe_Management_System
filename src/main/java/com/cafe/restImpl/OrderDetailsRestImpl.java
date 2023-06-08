package com.cafe.restImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cafe.entity.OrderInput;
import com.cafe.rest.OrderDetailsRest;
import com.cafe.service.OrderDetailsService;


@ComponentScan
@RestController
public class OrderDetailsRestImpl implements OrderDetailsRest {

    @Autowired
    OrderDetailsService orderDetailsService;

    @Override
    public void placeOrder(OrderInput orderInput )
    
    {

      try {
            orderDetailsService.placeOrder(orderInput);
       } catch (Exception ex) {
           ex.printStackTrace();
     }
     
    }
    
}
