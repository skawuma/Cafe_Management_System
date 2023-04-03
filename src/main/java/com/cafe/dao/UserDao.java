package com.cafe.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;

import com.cafe.entity.User;

import jakarta.transaction.Transactional;
/*
samuelkawuma
4:16:53 PM
Apr 3, 2023
*/
public interface UserDao extends JpaRepository<User,Integer> {


    User findByEmailId(@Param("email") String email);

   // List<UserWrapper> getAllUser();

    List<String> getAllAdmin();

    @Transactional
    @Modifying
    Integer updateStatus(@Param("status") String status, @Param("id") Integer id);

    User findByEmail(String email);
	
	
	
}
