package com.cafe.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe.dao.RoleDao;
import com.cafe.entity.Role;
import com.cafe.service.RoleService;

/*
samuelkawuma
4:15:49 PM
Apr 8, 2023
*/

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired	
	RoleDao roleDao;
	
	
	@Override
	public Role createNewRole(Role role) {	
		return   roleDao.save(role);
	}

}
