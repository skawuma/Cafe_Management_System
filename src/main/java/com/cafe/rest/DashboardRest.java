package com.cafe.rest;
/*
samuelkawuma
1:06:33 AM
Apr 1, 2023
*/

import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(path="/dashboard")
public interface DashboardRest {
	
	ResponseEntity<Map<String,Object>> getCount();

}
