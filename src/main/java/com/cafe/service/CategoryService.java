package com.cafe.service;

import java.util.Map;

import org.springframework.http.ResponseEntity;

/*
samuelkawuma
9:22:04 AM
Apr 5, 2023
*/
public interface CategoryService {
	
	  ResponseEntity<String> addNewCategory(Map<String, String> requestMap);
	
	
	
	

}
