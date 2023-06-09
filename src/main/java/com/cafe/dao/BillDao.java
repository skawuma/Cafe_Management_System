package com.cafe.dao;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.cafe.entity.Bill;


/*
samuelkawuma
10:20:55 AM
Apr 2, 2023
*/
public interface BillDao extends JpaRepository<Bill, Integer> {
	
	
	List<Bill> getAllBills();

    List<Bill> getBillByUserName(@Param("username") String username);
	

}
