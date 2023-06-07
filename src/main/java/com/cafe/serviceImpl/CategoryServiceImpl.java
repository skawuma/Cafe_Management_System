package com.cafe.serviceImpl;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.cafe.constants.CafeConstants;
import com.cafe.dao.CategoryDao;
import com.cafe.entity.Category;
import com.cafe.entity.TransactionDetails;
import com.cafe.jwt.JwtFilter;
import com.cafe.service.CategoryService;
import com.cafe.utils.CafeUtils;
import com.google.common.base.Strings;
import com.google.gson.JsonObject;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

import lombok.extern.slf4j.Slf4j;

/*
samuelkawuma
9:24:57 AM
Apr 5, 2023
 */


@Slf4j
@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	CategoryDao categoryDao;

	@Autowired
	JwtFilter  jwtFilter;
	

 private static final String KEY="rzp_test_HZAU1fzrIqlcFe";
 private static final String KEY_SECRET="K8v3dIgKBCuPKuYFsCRewRyC";
 private static final String   CURRENCY ="USD";




	static final org.slf4j.Logger log = LoggerFactory.getLogger(SpringApplication.class);

	
    @Override
	public void initCategory(){

     Category cat1 = new Category();
	 cat1.setId(1);
	 cat1.setName("Pizzas");
     categoryDao.save(cat1);
	 


     Category cat2 = new Category();
	 cat2.setId(2);
	 cat2.setName("Subs");
     categoryDao.save(cat2);


     Category cat3 = new Category();
	 cat3.setId(3);
	 cat3.setName("Canolis");
     categoryDao.save(cat3);



     Category cat4 = new Category();
	 cat4.setId(4);
	 cat4.setName("Cakes");
     categoryDao.save(cat4);
	






	}




	@Override
	public ResponseEntity<String> addNewCategory(Map<String, String> requestMap) {
		try {
			if (jwtFilter.isAdmin()) {
				if (validateCategoryMap(requestMap, false)) {
					categoryDao.save(getCategoryFromMap(requestMap, false));
					return CafeUtils.getResponseEntity("Category Added Successfully", HttpStatus.OK);
				}
			} else {
				return CafeUtils.getResponseEntity(CafeConstants.UNAUTHORIZED_ACCESS, HttpStatus.UNAUTHORIZED);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	private boolean validateCategoryMap(Map<String, String> requestMap, boolean validateId) {
		if (requestMap.containsKey("name")) {
			if (requestMap.containsKey("id") && validateId) {
				return true;
			} else if (!validateId) {
				return true;
			}
		}
		return false;
	}

	
	private Category getCategoryFromMap(Map<String, String> requestMap, Boolean isAdd) {
		
		Category category = new Category();
		
		
		if (isAdd) {
			
			category.setId(Integer.parseInt(requestMap.get("id")));
		}
		
		category.setName(requestMap.get("name"));
	
		return category;
	}

	
	@Override
	public ResponseEntity<List<Category>> getAllCategory(String filterValue) {
		try {
			if (!Strings.isNullOrEmpty(filterValue) && filterValue.equalsIgnoreCase("true")) {
				log.info("Inside if");
				return new ResponseEntity<List<Category>>(categoryDao.getAllCategory(), HttpStatus.OK);
			}
			return new ResponseEntity<>(categoryDao.findAll(), HttpStatus.OK);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ResponseEntity<List<Category>>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<String> updateCategory(Map<String, String> requestMap) {
		try {
            if (jwtFilter.isAdmin()) {
                if (validateCategoryMap(requestMap, true)) {
                    Optional optional = categoryDao.findById(Integer.parseInt(requestMap.get("id")));
                    if (!optional.isEmpty()) {
                        categoryDao.save(getCategoryFromMap(requestMap, true));
                        return CafeUtils.getResponseEntity("Category Updated Successfully", HttpStatus.OK);
                    } else {
                        return CafeUtils.getResponseEntity("Category id does not exist", HttpStatus.OK);
                    }
                }
                return CafeUtils.getResponseEntity(CafeConstants.INVALID_DATA, HttpStatus.BAD_REQUEST);
            } else {
                return CafeUtils.getResponseEntity(CafeConstants.UNAUTHORIZED_ACCESS, HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }




	@Override
	public TransactionDetails createTransaction(Integer amount) {
		
		try {

			
			JSONObject jsonObject = new JSONObject();

            jsonObject.put("amount",amount *100);
			jsonObject.put("currency",CURRENCY); 
		//jsonObject.pu
			RazorpayClient razorpayClient = new RazorpayClient(KEY, KEY_SECRET);


			Order order = razorpayClient.orders.create(jsonObject);

          return  prepareTransactionDetails(order);
			// System.out.println(order);

		} catch (RazorpayException e) {
			
			e.printStackTrace();
		} catch (JSONException e) {
			
			e.printStackTrace();
		}
		return null;
	}
	

	private TransactionDetails prepareTransactionDetails (Order order){


       String orderId = order.get("id");
	   String currency = order.get("currency");

	   Integer amount = order.get("amount");

      TransactionDetails transactionDetails = new TransactionDetails(orderId, currency, amount, KEY);

	  return transactionDetails;
	}
	

}
