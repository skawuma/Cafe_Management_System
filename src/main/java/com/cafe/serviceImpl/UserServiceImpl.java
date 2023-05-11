package com.cafe.serviceImpl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cafe.constants.CafeConstants;
import com.cafe.dao.RoleDao;
import com.cafe.dao.UserDao;
import com.cafe.entity.JwtRequest;
import com.cafe.entity.JwtResponse;
import com.cafe.entity.RegisterRequest;
import com.cafe.entity.Role;
import com.cafe.entity.User;
import com.cafe.jwt.CustomerUserDetailService;
import com.cafe.jwt.JwtFilter;
import com.cafe.jwt.JwtUtil;
import com.cafe.service.RoleService;
import com.cafe.service.UserService;
import com.cafe.utils.CafeUtils;
import com.cafe.utils.EmailUtils;
import com.cafe.wrapper.UserWrapper;
import com.google.common.base.Strings;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

/*
samuelkawuma
12:23:27 PM
Apr 9, 2023
*/

@Slf4j
@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	RoleDao roleDao;
	
	RoleService roleService;
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	CustomerUserDetailService customerUsersDetailsService;
	
	@Autowired
	JwtUtil jwtUtil;
	
	@Autowired
	EmailUtils emailUtils;
	
	
	
	@Autowired
	JwtFilter jwtFilter;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	 static final org.slf4j.Logger log = LoggerFactory.getLogger(SpringApplication.class);

	@Override
	public ResponseEntity<String> signUp(Map<String, String> requestMap) {
		log.info("Inside signup {}", requestMap);
        try {
            if (validateSignUpMap(requestMap)) {
                User user = userDao.findByEmailId(requestMap.get("email"));
                if (Objects.isNull(user)) {
                    userDao.save(getUserFromMap(requestMap));
                    return CafeUtils.getResponseEntity("Successfully Registered.", HttpStatus.OK);
                } else {
                    return CafeUtils.getResponseEntity("Email already exits.", HttpStatus.BAD_REQUEST);
                }
            } else {
                return CafeUtils.getResponseEntity(CafeConstants.INVALID_DATA, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private boolean validateSignUpMap(Map<String, String> requestMap) {
        if (requestMap.containsKey("name") && requestMap.containsKey("contactNumber")
                && requestMap.containsKey("email") && requestMap.containsKey("password")) {
            return true;
        }
        return false;
    }

    private User getUserFromMap(Map<String, String> requestMap) {
        User user = new User();
        Role role = roleDao.findById("User").get();
        
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(role);
        user.setRoles2(userRoles);

    //    Set<Role> role = new HashSet<>();
    //    Role userRole = new Role();
    //    userRole.setRoleName("User");
    //    roleDao.save(userRole);
    //    role.add(userRole);

        user.setName(requestMap.get("name"));
        user.setContactNumber(requestMap.get("contactNumber"));
        user.setEmail(requestMap.get("email"));
        user.setPassword(requestMap.get("password"));
        user.setRole("User");
        user.setStatus("false");
        // user.setRole(role);
        return user;
    }
    
    
    @Override
    public void initRoleAndUser(){
    	Role adminRole = new Role();
    	adminRole.setRoleName("Admin");
    	adminRole.setRoleDescription("Admin role");
    	roleDao.save(adminRole);
    	Role userRole = new Role();
    	userRole.setRoleName("User");
    	userRole.setRoleDescription("Default role for newly created record");
    	roleDao.save(userRole);


 

        User adminUser = new User();
        adminUser.setId((1));
    	adminUser.setUserName("admin123"); 
    	adminUser.setPassword(getEncodedPassword("admin@pass"));
    	adminUser.setName("Administrator1");
        adminUser.setContactNumber("99999");
        adminUser.setEmail("admin@gmail.com");
    	adminUser.setStatus("true");
        adminUser.setRole("Admin");
    	Set<Role> adminRoles = new HashSet<>();
    	adminRoles.add(adminRole);
    	adminUser.setRoles2(adminRoles);
    	userDao.save(adminUser);


    	User user = new User();
        user.setId((2));
    	user.setUserName("ska"); 
    	user.setPassword(getEncodedPassword("ska@pass"));
    	user.setName("SamuelKawuma");
    	user.setContactNumber("989898");
    	user.setEmail("ska@gmail.com");
    	user.setStatus("true");
        user.setRole("User");
    	Set<Role> userRoles = new HashSet<>();
    	userRoles.add(userRole);
    	user.setRoles2(userRoles);
    	userDao.save(user); 


        User user1 = new User();
        user1.setId((3));
    	user1.setUserName("dka"); 
    	user1.setPassword(getEncodedPassword("123"));
    	user1.setName("Deborah Katimbo");
    	user1.setContactNumber("9784949594");
    	user1.setEmail("cynthiamuganzi@gmail.com");
    	user1.setStatus("true");
        user1.setRole("User");
    	Set<Role> userRoles1 = new HashSet<>();
    	userRoles1.add(userRole);
    	user.setRoles2(userRoles1);
    	userDao.save(user1); 


        User user2 = new User();
        user2.setId((4));
    	user2.setUserName("christo"); 
    	user2.setPassword(getEncodedPassword("321"));
    	user2.setName("Christopher Kawuma");
    	user2.setContactNumber("9783982410");
    	user2.setEmail("christokawuma69@gmail.com");
    	user2.setStatus("true");
        user2.setRole("User");
    	Set<Role> userRoles2 = new HashSet<>();
    	userRoles2.add(userRole);
    	user.setRoles2(userRoles2);
    	userDao.save(user2); 



        User adminUser1 = new User();
        adminUser1.setId((5));
    	adminUser1.setUserName("admin321"); 
    	adminUser1.setPassword(getEncodedPassword("admin@pass123"));
    	adminUser1.setName("Administrator2");
        adminUser1.setContactNumber("98787874");
        adminUser1.setEmail("admin@mailinator.com");
    	adminUser1.setStatus("true");
        adminUser1.setRole("Admin2");
    	Set<Role> adminRoles1 = new HashSet<>();
    	adminRoles1.add(adminRole);
    	adminUser1.setRoles2(adminRoles1);
    	userDao.save(adminUser1);

    	}
    
    
    	public String getEncodedPassword( String password){
    		return passwordEncoder.encode(password);

    	}
    
    
    
    

	@Override
    public ResponseEntity<String> login(Map<String, String> requestMap) {
        log.info("Inside login");
        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(requestMap.get("email"), requestMap.get("password"))
            );
            if (auth.isAuthenticated()) {
                 User user = new User();
                if (user.getStatus().equalsIgnoreCase("true")) {
                   
                    UserDetails userDetails = customerUsersDetailsService.loadUserByUsername(user.getEmail());
                    return new ResponseEntity<String>("{\"token\":\"" +
                            jwtUtil.generateToken2(userDetails) + "\"}",
                            HttpStatus.OK);
                } else {
                    return new ResponseEntity<String>("{\"message\":\"" + "Wait for admin approval." + "\"}",
                            HttpStatus.BAD_REQUEST);
                }
            }
        } catch (Exception ex) {
            log.error("{}", ex);
        }
        return new ResponseEntity<String>("{\"message\":\"" + "Bad Credentials." + "\"}",
                HttpStatus.BAD_REQUEST);
    }




    // @Override
    // public ResponseEntity<String> login(Map<String, String> requestMap) {
    //     log.info("Inside login");
    //     try {
    //         User user = new User();
    //          user = userDao.findByEmailId1(user.getEmail()).get();
    //        // String email = user.getEmail();
    //         //String password = user.getPassword();
    //         Authentication auth = authenticationManager.authenticate(
    //                 new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword())
    //         );
    //         if (auth.isAuthenticated()) {

               
    //             Role role = roleDao.findById("Admin").get();
    //            Role role1 = roleDao.findById("User").get();
    //           String roleDuty= user.getRoles();
     
    //            Set<Role> userRoles = new HashSet<>();
    //           if(user.getEmail().contains("admin@gmail.com")) {
    //           userRoles.add(role);
    //            user.setRoles(roleDuty);
    //            }else{ 
    //             userRoles.add(role1);
    //            user.setRoles(roleDuty);
    //     }
    //          user.setRole(userRoles);
            
     
    //         userDao.save(user);

    //             if (customerUsersDetailsService.getUserDetail().getStatus().equalsIgnoreCase("true")) {
    //                 UserDetails userDetails = customerUsersDetailsService.loadUserByUsername(user.getEmail());


    //                 return new ResponseEntity<String>("{\"token\":\"" +
    //                         jwtUtil.generateToken2(userDetails) + "\"}",
    //                         HttpStatus.OK);
    //             } else {
    //                 return new ResponseEntity<String>("{\"message\":\"" + "Wait for admin approval." + "\"}",
    //                         HttpStatus.BAD_REQUEST);
    //             }
    //         }
    //     } catch (Exception ex) {
    //         log.error("{}", ex);
    //     }
    //     return new ResponseEntity<String>("{\"message\":\"" + "Bad Credentials." + "\"}",
    //             HttpStatus.BAD_REQUEST);
    // }

	

   

	
	
	@Transactional
	@Override
	public ResponseEntity<List<UserWrapper>> getAllUser() {

		try {
        //    if (jwtFilter.isAdmin()) {
            
                return new ResponseEntity<>(userDao.getAllUser(), HttpStatus.OK);
          //  }
            
            //  else {
            //     return new ResponseEntity<>(new ArrayList<>(), HttpStatus.UNAUTHORIZED);
            // }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

	@Override
	public ResponseEntity<String> update(Map<String, String> requestMap) {
		try {
          //  if (jwtFilter.isAdmin()) {
                Optional<User> optional = userDao.findById(Integer.parseInt(requestMap.get("id")));
                if (!optional.isEmpty()) {
                    userDao.updateStatus(requestMap.get("status"), Integer.parseInt(requestMap.get("id")));
                    sendMailToAllAdmin(requestMap.get("status"), optional.get().getEmail(), userDao.getAllAdmin());
                    return CafeUtils.getResponseEntity("User Status Updated Successfully", HttpStatus.OK);
                } else {
                    return CafeUtils.getResponseEntity("User id doesn't not exist", HttpStatus.OK);
                }
            // } else {
            //     return CafeUtils.getResponseEntity(CafeConstants.UNAUTHORIZED_ACCESS, HttpStatus.UNAUTHORIZED);
            // }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }
	 
	
	
	
	private void sendMailToAllAdmin(String status, String user, List<String> allAdmin) {
        allAdmin.remove(jwtFilter.getCurrentUser());
        if (status != null && status.equalsIgnoreCase("true")) {
            emailUtils.sendSimpleMessage(jwtFilter.getCurrentUser(), "Account Approved", "USER:- " + user + " \n is approved by \nADMIN:-" + jwtFilter.getCurrentUser(), allAdmin);
        } else {
            emailUtils.sendSimpleMessage(jwtFilter.getCurrentUser(), "Account Disabled", "USER:- " + user + " \n is disabled by \nADMIN:-" + jwtFilter.getCurrentUser(), allAdmin);
        }
    }

	@Override
	public ResponseEntity<String> checkToken() {
		return CafeUtils.getResponseEntity("true", HttpStatus.OK);
    }
	

	@Override
	public ResponseEntity<String> changePassword(Map<String, String> requestMap) {
		try {
            // User userObj =new User();
            //      userObj = userDao.findByEmailId1(userObj.getEmail()).get();
              User userObj = userDao.findByEmail(jwtFilter.getCurrentUser());
            if (!userObj.equals(null)) {
                if (userObj.getPassword().equals(requestMap.get("oldPassword"))) {
                    userObj.setPassword(requestMap.get("newPassword"));
                    userDao.save(userObj);
                    return CafeUtils.getResponseEntity("Password Updated Successfully", HttpStatus.OK);
                }
                return CafeUtils.getResponseEntity("Incorrect Old Password", HttpStatus.BAD_REQUEST);
            }
            return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

	@Override
	public ResponseEntity<String> forgotPassword(Map<String, String> requestMap) {
		try {
            User user = userDao.findByEmail(requestMap.get("email"));
            if (!Objects.isNull(user) && !Strings.isNullOrEmpty(user.getEmail()))
                emailUtils.forgotMail(user.getEmail(), "Credentials by Cafe Management System", user.getPassword());
            return CafeUtils.getResponseEntity("Check your mail for Credentials.", HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private void authenticate(String email, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }





	@Override
	public JwtResponse createJwtToken(JwtRequest jwtRequest)throws Exception {
	   
        User user = new User();
        String email = jwtRequest.getEmail();
        String password = jwtRequest.getPassword();
        authenticate(email, password);

      
    
        Role role = roleDao.findById("Admin").get();
       Role role1 = roleDao.findById("User").get();
      // String roleDuty=  customerUsersDetailsService.getUserDetail().getRoles();
      String roleDuty= user.getRole();
     
         Set<Role> userRoles = new HashSet<>();
        
         if( email.contains("admin@gmail.com")) {
 
         userRoles.add(role);
         user.setRole(roleDuty);
 
     }else{
         
          userRoles.add(role1);
          user.setRole(roleDuty);
     }
 
         user.setRoles2(userRoles);
         


       user = userDao.findByEmailId1(email).get();
        
         userDao.save(user);
     UserDetails userDetails = customerUsersDetailsService.loadUserByUsername(email);
    

     //   String newGeneratedToken = jwtUtil.generateToken2(userDetails);

        String newGeneratedToken = jwtUtil.generateToken(user.getEmail(),user.getRole());
        return new JwtResponse(user, newGeneratedToken);
        
	}



    @Override
    public Iterable<User> displayAllUsers() {
       return userDao.findAll();
    }
	}


