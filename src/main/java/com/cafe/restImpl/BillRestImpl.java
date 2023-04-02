package com.cafe.restImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.cafe.entity.Bill;
import com.cafe.rest.BillRest;
import com.cafe.service.BillService;


/*
samuelkawuma
8:45:48 AM
Apr 2, 2023
*/

@RestController
public class BillRestImpl implements BillRest {

	@Autowired
	BillService billService;
	
	@Override
	public ResponseEntity<String> generateReport(Map<String, Object> requestMap) {
		
		
		try {
			
			return billService.generateReport(requestMap);
			
		}catch (Exception ex) {
			
			ex.printStackTrace();
		}
		
		return null;
	}

	@Override
	public ResponseEntity<List<Bill>> getBills() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<byte[]> getPdf(Map<String, Object> requestMap) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<String> deleteBill(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}
