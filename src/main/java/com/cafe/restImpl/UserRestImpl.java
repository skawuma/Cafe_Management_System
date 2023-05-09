package com.cafe.restImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.cafe.constants.CafeConstants;
import com.cafe.entity.JwtRequest;
import com.cafe.entity.JwtResponse;
import com.cafe.entity.RegisterRequest;
import com.cafe.entity.User;
import com.cafe.rest.UserRest;
import com.cafe.service.UserService;
import com.cafe.utils.CafeUtils;
import com.cafe.wrapper.UserWrapper;

import jakarta.annotation.PostConstruct;

/*
samuelkawuma
9:00:35 AM
Apr 9, 2023
*/
@ComponentScan
@RestController
public class UserRestImpl implements UserRest {
	
	
	@Autowired
	UserService userService;
	
	private static final String String = null;
	private static final User User = null;

	@Override
	public ResponseEntity<String> signUp(Map<String, String> requestMap) {
		try {
            return userService.signUp(requestMap);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

	@Override
	public ResponseEntity<String> login(Map<String, String> requestMap) {
		try {
            return userService.login(requestMap);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

	@Override
	public ResponseEntity<List<UserWrapper>> getAllUser() {
		try {
            return userService.getAllUser();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<List<UserWrapper>>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

	@Override
	public ResponseEntity<String> update(Map<String, String> requestMap) {
		try {
            return userService.update(requestMap);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

	@Override
	public ResponseEntity<String> checkToken() {
		try {
            return userService.checkToken();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

	@Override
	public ResponseEntity<String> changePassword(Map<String, String> requestMap) {
		try {
            return userService.changePassword(requestMap);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

	@Override
	public ResponseEntity<String> forgotPassword(Map<String, String> requestMap) {
		try {
            return userService.forgotPassword(requestMap);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

	 @PostConstruct
	public void intitRoleAndUser() {
		userService.initRoleAndUser();
		
	}

	@Override
	public JwtResponse createJwtToken(JwtRequest jwtRequest) {
		try {
			 
            return userService.createJwtToken(jwtRequest);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new JwtResponse(User, String);
    }

    // @Override
    // public JwtResponse register(RegisterRequest request) {
    //     try {
			 
    //         return userService.register(request);
    //     } catch (Exception ex) {
    //         ex.printStackTrace();
    //     }
    //     return new JwtResponse(User, String);
    // }

    @Override
    public Iterable<User> readAllUsers() {
        return userService.displayAllUsers();
    }
		
	

}
