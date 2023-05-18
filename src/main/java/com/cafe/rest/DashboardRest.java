package com.cafe.rest;
/*
samuelkawuma
1:06:33 AM
Apr 1, 2023
*/

import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(path="/dashboard")
@CrossOrigin(origins = "http://localhost:4200")
public interface DashboardRest {
	
	@GetMapping(path = "/details")
	ResponseEntity<Map<String,Object>> getCount();

}
