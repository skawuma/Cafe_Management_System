package com.cafe.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.cafe.wrapper.ProductWrapper;

/*
samuelkawuma
4:55:27 PM
Apr 8, 2023
*/
public interface ProductService {

	ResponseEntity<String> addNewProduct(Map<String, String> requestMap);

	ResponseEntity<List<ProductWrapper>> getAllProduct();

	ResponseEntity<String> updateProduct(Map<String, String> requestMap);

	ResponseEntity<String> deleteProduct(Integer id);

	ResponseEntity<String> updateStatus(Map<String, String> requestMap);

	ResponseEntity<List<ProductWrapper>> getByCategory(Integer id);

	ResponseEntity<ProductWrapper> getProductById(Integer id);

}
