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
12:42:11 PM
Mar 29, 2023
*/

@Data
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name = "bill")
public class Bill implements Serializable {
	
	
private static final long serialVersionUID = 1L;

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name= "id")
private  Integer id;

@Column(name ="uuid")
private String uuid;

@Column(name ="name")
private String name;

@Column(name ="email")
private String email;

@Column(name ="contactNumber")
private String contactNumber;

@Column(name ="paymentMethod")
private String paymentMethod;

@Column(name ="total")
private Integer total
;
@Column(name ="productDetail", columnDefinition ="json")
private String productDetail;

@Column(name ="uuid")
private String createdBy;


}
