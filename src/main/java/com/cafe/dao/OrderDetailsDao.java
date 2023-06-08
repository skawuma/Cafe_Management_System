package com.cafe.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cafe.entity.OrderDetails;

public interface OrderDetailsDao extends  JpaRepository<OrderDetails,Integer> {
    
}
