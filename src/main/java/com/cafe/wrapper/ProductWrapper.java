package com.cafe.wrapper;
/*
samuelkawuma
11:46:51 PM
Apr 3, 2023
*/

import lombok.Data;

@Data
public class ProductWrapper {
	
	
	Integer id;
	
	String name;
	
	String description;
	
	Integer price;
	
	String status;
	
	Integer categoryId;
	
	
	String categoryName;
	
	
	


	public ProductWrapper() {
		super();
		
	}



	public ProductWrapper(Integer id, String name, String description, Integer price) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
	}



	public ProductWrapper(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}



	public ProductWrapper(Integer id, String name, String description, Integer price, String status, Integer categoryId,
			String categoryName) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.status = status;
		this.categoryId = categoryId;
		this.categoryName = categoryName;
	}
	
	

}
