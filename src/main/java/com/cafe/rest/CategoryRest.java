package com.cafe.rest;
/*
samuelkawuma
1:05:41 AM
Apr 1, 2023
*/

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cafe.entity.Category;

@RequestMapping(path = "/category")
public interface CategoryRest {
	
	
    @PostMapping(path = "/add")
    ResponseEntity<String> addNewCategory(@RequestBody(required = true)
                                                  Map<String, String> requestMap);
    
    @GetMapping(path = "/get")
    ResponseEntity<List<Category>> getAllCategory(@RequestParam(required = false)
                                                          String filterValue);

    @PutMapping(path = "/update")
    ResponseEntity<String> updateCategory(@RequestBody(required = true)
                                                  Map<String, String> requestMap);
    
	

}
