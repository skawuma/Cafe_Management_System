package com.cafe.rest;
/*
samuelkawuma
1:05:41 AM
Apr 1, 2023
*/

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(path = "/category")
public interface CategoryRest {
	
	
    @PostMapping(path = "/add")
    ResponseEntity<String> addNewCategory(@RequestBody(required = true)
                                                  Map<String, String> requestMap);
                                                  
	
    
    
	

}
