package com.cafe.entity;
/*
samuelkawuma
1:01:20 PM
Apr 14, 2023
*/

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
	private String role1 = "User";

	
	private String email;
	private String password;
	private String name;
	private String contactNumber;
	private Set<Role> role;

	
	

	public Set<Role> getRole() {
		return role;
	}

	// public void setRole(Set<Role> role) {
	// 	this.role = role;
	// }
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public  void setRole1() {
		 this.role1=role1;
	}
	public String getRole1() {
		return role1;
	}

	
}
