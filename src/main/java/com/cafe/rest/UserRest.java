package com.cafe.rest;
/*
samuelkawuma
1:07:44 AM
Apr 1, 2023
*/

import java.util.List;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cafe.entity.JwtRequest;
import com.cafe.entity.JwtResponse;
import com.cafe.entity.RegisterRequest;
import com.cafe.entity.User;
import com.cafe.wrapper.UserWrapper;

import jakarta.annotation.PostConstruct;
// @RestController
@RequestMapping(path = "/user")
@CrossOrigin(origins = "http://localhost:4200")

public interface UserRest {
	
	
	@PostMapping(path = "/signup")
    public ResponseEntity<String> signUp(@RequestBody(required = true) Map<String, String> requestMap);

    @PostMapping(path = "/login")
    public ResponseEntity<String> login(@RequestBody(required = true) Map<String, String> requestMap);

  // @PreAuthorize("hasRole('Admin')")
    @GetMapping(path = "/get")
    public ResponseEntity<List<UserWrapper>> getAllUser();


    //  @PreAuthorize("hasRole('Admin')")
    @GetMapping("/getall")
	public Iterable<User> readAllUsers();

    @PutMapping(path = "/update")
    public ResponseEntity<String> update(@RequestBody(required = true) Map<String, String> requestMap);

    @GetMapping(path = "/checkToken")
    ResponseEntity<String> checkToken();

    @PostMapping(path = "/changePassword")
    ResponseEntity<String> changePassword(@RequestBody Map<String, String> requestMap);

    @PostMapping(path = "/forgotPassword")
    ResponseEntity<String> forgotPassword(@RequestBody Map<String, String> requestMap);
    
    @PostMapping(path ="/login1")
    public JwtResponse createJwtToken(@RequestBody JwtRequest jwtRequest);
  
    public void  intitRoleAndUser();

    // @PostMapping(path = "/signup1")
    // public JwtResponse register(@RequestBody RegisterRequest request);

}
