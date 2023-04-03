package com.cafe.jwt;

import java.util.HashSet;
import java.util.Objects;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cafe.dao.UserDao;
import com.cafe.entity.User;
import java. util.Optional;
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
	UserDao userDao;
	

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ///modify toUsers user = userRepo.findByUserName(username).get();
    	User user = userDao.findByEmailId(username);
        if (user != null) {
            return new org.springframework.security.core.userdetails.User(
                    user.getUsername(),
                    user.getPassword(),
                    getAuthority(user)
            );
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }

    private Set getAuthority(User user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        user.getRole().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
        });
        return authorities;
    }
}
