package com.cafe.rest;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cafe.entity.OrderInput;

@RequestMapping
//(path = "/user")
// @CrossOrigin(origins = "http://localhost:4200")
public interface OrderDetailsRest {
 


    @PostMapping( path ="/placeOrder")
    public void placeOrder(@RequestBody OrderInput OrderInput );

        


     
}
