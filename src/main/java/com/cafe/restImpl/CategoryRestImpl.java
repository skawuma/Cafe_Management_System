package com.cafe.restImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.cafe.constants.CafeConstants;
import com.cafe.entity.Category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import com.cafe.service.CategoryService;
import com.cafe.rest.CategoryRest;
import com.cafe.utils.CafeUtils;

import jakarta.annotation.PostConstruct;

/*
samuelkawuma
9:19:05 AM
Apr 5, 2023
*/
@RestController
public class CategoryRestImpl implements CategoryRest {
	@Autowired
	CategoryService categoryService;
	@Override
	public ResponseEntity<String> addNewCategory(Map<String, String> requestMap) {
        try {
            return categoryService.addNewCategory(requestMap);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);

}
@PostConstruct
public void initCategory()
{
	categoryService.initCategory();

}
	@Override
	public ResponseEntity<List<Category>> getAllCategory(String filterValue) {
		try {
            return categoryService.getAllCategory(filterValue);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
	
	
	@Override
	public ResponseEntity<String> updateCategory(Map<String, String> requestMap) {
		 try {
	            return categoryService.updateCategory(requestMap);
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	        return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
}
}