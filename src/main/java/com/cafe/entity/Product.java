package com.cafe.entity;

import java.io.Serializable;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

/*
samuelkawuma
12:40:18 PM
Mar 29, 2023
*/

@Data
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name = "bill")

public class Product implements Serializable {

	
private static final long serialVersionUID = 123456L;

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name= "id")
private  Integer id;

@Column(name ="name")
private String name;

@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "category_fk", nullable = false)
private Category category;

@Column(name ="description")
private String description;


@Column(name ="price")
private Integer price;


@Column(name ="status")
private String status;





}
