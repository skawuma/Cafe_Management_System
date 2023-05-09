package com.cafe.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cafe.entity.Role;

/*
samuelkawuma
4:08:35 PM
Apr 8, 2023
*/
public interface RoleDao  extends JpaRepository <Role, String>{

    // @Query(value = "SELECT * FROM CafeSystem.role  where roleName = ?1", nativeQuery=true)
	// public Optional <Role> findByRoleName(String roleName);


    // @Query(value = "SELECT * FROM CafeSystem.role")
    // public List <Role> getallUserRoles();

}
