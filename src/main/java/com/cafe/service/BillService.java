package com.cafe.service;

import java.util.Map;

import org.springframework.http.ResponseEntity;

/*
samuelkawuma
8:57:22 AM
Apr 2, 2023
*/
public interface BillService {

	ResponseEntity<String> generateReport(Map<String, Object> requestMap);

	
	
	
}
