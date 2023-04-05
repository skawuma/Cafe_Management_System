package com.cafe.restImpl;

import java.util.Map;
import com.cafe.constants.CafeConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import com.cafe.service.CategoryService;
import com.cafe.rest.CategoryRest;
import com.cafe.utils.CafeUtils;

/*
samuelkawuma
9:19:05 AM
Apr 5, 2023
*/
@RestController
public class CategoryRestImpl implements CategoryRest {
	
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
}
