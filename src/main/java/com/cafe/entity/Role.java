package com.cafe.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/*
samuelkawuma
4:39:57 PM
Apr 3, 2023
*/


@Entity
@Table(name = "role")
public class Role {
	
	@Id
	private String roleName;
	private String roleDescription;

	 
	public String getRoleName() {
	    return roleName;
	}
	public void setRoleName(String roleName) {
	    this.roleName = roleName;
	}
	public String getRoleDescription() {
	    return roleDescription;
	}
	public void setRoleDescription(String roleDescription) {
	    this.roleDescription = roleDescription;
	}


}
