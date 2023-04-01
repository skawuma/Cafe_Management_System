package com.cafe.entity;

import java.io.Serializable;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/*
samuelkawuma
12:43:10 PM
Mar 29, 2023
*/


@Data
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name = "user")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name= "id")
	private  Integer id;
	
	
	@Column(name ="name")
	private String name;
	
	@Column(name ="contactNumber")
	private String contactNumber;
	
	@Column(name ="email")
	private String email;
	
	@Column(name ="role")
	private String role;
	
}
