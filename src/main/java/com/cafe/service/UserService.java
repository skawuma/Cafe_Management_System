package com.cafe.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.cafe.entity.JwtRequest;
import com.cafe.entity.JwtResponse;
import com.cafe.entity.RegisterRequest;
import com.cafe.wrapper.UserWrapper;

/*
samuelkawuma
4:56:00 PM
Apr 8, 2023
*/
public interface UserService {

	ResponseEntity<String> signUp(Map<String, String> requestMap);

	ResponseEntity<String> login(Map<String, String> requestMap);

	ResponseEntity<List<UserWrapper>> getAllUser();

	ResponseEntity<String> update(Map<String, String> requestMap);

	ResponseEntity<String> checkToken();

	ResponseEntity<String> changePassword(Map<String, String> requestMap);

	ResponseEntity<String> forgotPassword(Map<String, String> requestMap);

	public void initRoleAndUser();

	public JwtResponse createJwtToken(JwtRequest jwtRequest) throws Exception;

    public  JwtResponse register(RegisterRequest request);

}
