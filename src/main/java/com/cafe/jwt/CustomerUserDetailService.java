package com.cafe.jwt;


import java.util.HashSet;
import java.util.Objects;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cafe.dao.UserDao;

import com.cafe.entity.User;

import java.util.Set;

import lombok.extern.slf4j.Slf4j;

/*
samuelkawuma
4:11:03 PM
Apr 3, 2023
*/

@Slf4j
@Service
public class CustomerUserDetailService implements UserDetailsService {
		
	@Autowired
	UserDao  userDao;

	
	private  com.cafe.entity.User userDetail;
    //private com.cafe.entity.Role roleDetail;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       
    	User user = userDao.findByEmailId(username);
        if (user != null) {
            return new org.springframework.security.core.userdetails.User(
            		
                    user.getEmail(),
                    user.getPassword(),
                    getAuthority(user)
                    
            );
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }

    private Set getAuthority(User user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        user.getRoles2().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
        });
        return authorities;
    }

	public com.cafe.entity.User getUserDetail() {
		return userDetail;
	}


  




    
}
