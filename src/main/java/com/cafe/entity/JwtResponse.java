package com.cafe.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
samuelkawuma
1:00:59 PM
Apr 14, 2023
*/
@Data
@Builder
//@AllArgsConstructor
@NoArgsConstructor
public class JwtResponse {
	
	private User user;
	
	private String jwtToken;

	public JwtResponse(User user, String jwtToken) {
		super();
		this.user = user;
		this.jwtToken = jwtToken;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getJwtToken() {
		return jwtToken;
	}

	public void setJwtToken(String jwtToken) {
		this.jwtToken = jwtToken;
	}

}
