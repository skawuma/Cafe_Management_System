package com.cafe.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cafe.entity.Category;

/*
samuelkawuma
9:32:33 AM
Apr 5, 2023
*/
public interface CategoryDao  extends  JpaRepository<Category, Integer>{
	
	
	 List<Category> getAllCategory();

}
