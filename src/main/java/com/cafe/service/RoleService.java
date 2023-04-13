package com.cafe.service;
/*
samuelkawuma
4:11:08 PM
Apr 8, 2023
*/

import java.util.Optional;

import com.cafe.entity.Role;
import com.cafe.entity.User;

public interface RoleService {
	
	public Role createNewRole(Role role);

	public Optional<Role> findById(String string);

}
