package com.cafe.entity;

import lombok.Data;
import lombok.NoArgsConstructor;


public class OrderProductQuantity {


    private Integer id;
    private Integer quantity;


    
    
    // @Override
    // public String toString() {
    //     return "OrderProductQuantity [id=" + id + ", quantity=" + quantity + "]";
    // }
    // public OrderProductQuantity() {


    // }
    // public OrderProductQuantity(Integer id, Integer quantity) {
    //     this.id = id;
    //     this.quantity = quantity;
    // }
    public Integer getId() {
        return id;
    }
    public Integer getQuantity() {
        return quantity;
    }   
    public void setId(Integer id) {
        this.id = id;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    } 
    

    
}
