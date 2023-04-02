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
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.Builder;

/*
samuelkawuma
12:42:11 PM
Mar 29, 2023
*/

@AllArgsConstructor
@Setter
@Getter
@Data
@Builder(builderClassName = "Builder", toBuilder = true)
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
private Integer total;


@Column(name ="productDetail", columnDefinition ="json")
private String productDetail;


@Column(name ="uuid")
private String createdBy;


public Integer getId() {
	return id;
}


public void setId(Integer id) {
	this.id = id;
}


public String getUuid() {
	return uuid;
}


public void setUuid(String uuid) {
	this.uuid = uuid;
}


public String getName() {
	return name;
}


public void setName(String name) {
	this.name = name;
}


public String getEmail() {
	return email;
}


public void setEmail(String email) {
	this.email = email;
}


public String getContactNumber() {
	return contactNumber;
}


public void setContactNumber(String contactNumber) {
	this.contactNumber = contactNumber;
}


public String getPaymentMethod() {
	return paymentMethod;
}


public void setPaymentMethod(String paymentMethod) {
	this.paymentMethod = paymentMethod;
}


public Integer getTotal() {
	return total;
}


public void setTotal(Integer total) {
	this.total = total;
}


public String getProductDetail() {
	return productDetail;
}


public void setProductDetail(String productDetail) {
	this.productDetail = productDetail;
}


public String getCreatedBy() {
	return createdBy;
}


public void setCreatedBy(String createdBy) {
	this.createdBy = createdBy;
}


public static long getSerialversionuid() {
	return serialVersionUID;
}


}
