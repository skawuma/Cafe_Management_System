package com.cafe.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cafe.entity.User;
import com.cafe.wrapper.UserWrapper;

import jakarta.transaction.Transactional;
/*
samuelkawuma
4:16:53 PM
Apr 3, 2023
*/

public interface UserDao extends JpaRepository<User,Integer> {


    User findByEmailId(@Param("email") String email);
    
    @Query(value = "SELECT * FROM CafeSystem.user where email = ?1", nativeQuery=true)
    public Optional <User> findByEmailId1(String email);
    
    List<UserWrapper> getAllUser();

    List<String> getAllAdmin();

    @Transactional
    @Modifying
    Integer updateStatus(@Param("status") String status, @Param("id") Integer id);

    User findByEmail(String email);
	
	
	
}
