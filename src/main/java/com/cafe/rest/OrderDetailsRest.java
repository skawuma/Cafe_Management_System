package com.cafe.rest;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cafe.entity.OrderDetails;
import com.cafe.entity.OrderInput;

@RequestMapping
//(path = "/user")
// @CrossOrigin(origins = "http://localhost:4200")
public interface OrderDetailsRest {
 

    // @PreAuthorize("hasRole('User')")
    @PostMapping({"/placeOrder"})
    public void placeOrder(@RequestBody OrderInput orderInput);

        
    @GetMapping("/getall")
	public Iterable<OrderDetails> readAllOrderDetails();

     
}
