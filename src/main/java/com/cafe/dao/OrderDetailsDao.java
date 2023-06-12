package com.cafe.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.cafe.entity.OrderDetails;

public interface OrderDetailsDao extends  JpaRepository<OrderDetails,Integer> {
    
}
