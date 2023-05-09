package com.cafe.wrapper;
/*
samuelkawuma
11:59:31 PM
Apr 3, 2023
*/

import java.util.Set;

import com.cafe.entity.Role;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserWrapper {
	
	
	private Integer id;
	
	private String name;
	
	private String email;
	
	private String  contactNumber;
	
	private String status;

	

	public UserWrapper(Integer id, String name, String email, String contactNumber, String status) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.contactNumber = contactNumber;
		this.status = status;
	
	}



}
