package com.cafe.service;

import java.util.Map;

import org.springframework.http.ResponseEntity;

/*
samuelkawuma
3:12:57 PM
Apr 8, 2023
*/
public interface DashboardService {

	ResponseEntity<Map<String, Object>> getCount();

}
