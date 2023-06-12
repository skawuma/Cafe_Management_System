package com.cafe.service;

import com.cafe.entity.OrderDetails;
import com.cafe.entity.OrderInput;






public interface OrderDetailsService {




    public  void placeOrder(OrderInput orderInput);

    public Iterable<OrderDetails> displayAllOrderDetails();



    }



    

  
    
