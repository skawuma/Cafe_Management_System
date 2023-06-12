package com.cafe.serviceImpl;
import java.util.Optional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.cafe.constants.CafeConstants;
import com.cafe.dao.CategoryDao;
import com.cafe.dao.ProductDao;
import com.cafe.entity.Category;
import com.cafe.entity.OrderProductQuantity;
import com.cafe.entity.Product;
import com.cafe.jwt.JwtFilter;
import com.cafe.service.ProductService;
import com.cafe.utils.CafeUtils;
import com.cafe.wrapper.ProductWrapper;

import lombok.Data;

/*
samuelkawuma
8:56:17 PM
Apr 8, 2023
*/


@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	ProductDao  productDao;
	
	
	@Autowired
	JwtFilter  jwtFilter;

    @Autowired
	CategoryDao categoryDao;

	@Override
	public ResponseEntity<String> addNewProduct(Map<String, String> requestMap) {
		try {
            if (jwtFilter.isAdmin()) {
                if (validateProductMap(requestMap, false)) {
                    productDao.save(getProductFromMap(requestMap, false));
                    return CafeUtils.getResponseEntity("Product Added Successfully.", HttpStatus.OK);
                }
                return CafeUtils.getResponseEntity(CafeConstants.INVALID_DATA, HttpStatus.BAD_REQUEST);
            } else
                return CafeUtils.getResponseEntity(CafeConstants.UNAUTHORIZED_ACCESS, HttpStatus.UNAUTHORIZED);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }
	
	private boolean validateProductMap(Map<String, String> requestMap, boolean validateId) {
        if (requestMap.containsKey("name")) {
            if (requestMap.containsKey("id") && validateId) {
                return true;
            } else if (!validateId) {
                return true;
            }
        }
        return false;
    }
	
	private Product getProductFromMap(Map<String, String> requestMap, boolean isAdd) {
        Category category = new Category();
        category.setId(Integer.parseInt(requestMap.get("categoryId")));

        Product product = new Product();
        if (isAdd) {
            product.setId(Integer.parseInt(requestMap.get("id")));
        } else {
            product.setStatus("true");
        }
        product.setCategory(category);
        product.setName(requestMap.get("name"));
        product.setDescription(requestMap.get("description"));
        product.setPrice(Integer.parseInt(requestMap.get("price")));
        return product;
    }
	
	

	@Override
	public ResponseEntity<List<ProductWrapper>> getAllProduct() {
		try {
            return new ResponseEntity<>(productDao.getAllProduct(), HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
	
	
	@Override
	public ResponseEntity<String> updateProduct(Map<String, String> requestMap) {
		try {
            if (jwtFilter.isAdmin()) {
                if (validateProductMap(requestMap, true)) {
                    Optional<Product> optional = productDao.findById(Integer.parseInt(requestMap.get("id")));
                    if (!optional.isEmpty()) {
                        Product product = getProductFromMap(requestMap, true);
                        product.setStatus(optional.get().getStatus());
                        productDao.save(product);
                        return CafeUtils.getResponseEntity("Product Updated Successfully", HttpStatus.OK);
                    } else {
                        return CafeUtils.getResponseEntity("Product id does not exist.", HttpStatus.OK);
                    }
                } else {
                    return CafeUtils.getResponseEntity(CafeConstants.INVALID_DATA, HttpStatus.BAD_REQUEST);
                }
            } else {
                return CafeUtils.getResponseEntity(CafeConstants.UNAUTHORIZED_ACCESS, HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

	@Override
	public ResponseEntity<String> deleteProduct(Integer id) {
		
		try {
            if (jwtFilter.isAdmin()) {
                Optional optional = productDao.findById(id);
                if (!optional.isEmpty()) {
                    productDao.deleteById(id);
                    return CafeUtils.getResponseEntity("Product Deleted Successfully", HttpStatus.OK);
                }
                return CafeUtils.getResponseEntity("Product id does not exist.", HttpStatus.OK);
            } else {
                return CafeUtils.getResponseEntity(CafeConstants.UNAUTHORIZED_ACCESS, HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }	
		
		
		
	@Override
	public ResponseEntity<String> updateStatus(Map<String, String> requestMap) {
		try {
            if (jwtFilter.isAdmin()) {
                Optional optional = productDao.findById(Integer.parseInt(requestMap.get("id")));
                if (!optional.isEmpty()) {
                    productDao.updateProductStatus(requestMap.get("status"), Integer.parseInt(requestMap.get("id")));
                    return CafeUtils.getResponseEntity("Product Status Upadted Successfully", HttpStatus.OK);
                }
                return CafeUtils.getResponseEntity("Product id does not exist", HttpStatus.OK);
            } else {
                return CafeUtils.getResponseEntity(CafeConstants.UNAUTHORIZED_ACCESS, HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

	@Override
	public ResponseEntity<List<ProductWrapper>> getByCategory(Integer id) {
		try {
            return new ResponseEntity<>(productDao.getProductByCategory(id), HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

	@Override
	public ResponseEntity<ProductWrapper> getProductById(Integer id) {
		try {
            return new ResponseEntity<>(productDao.getProductById(id), HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ProductWrapper(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

    @Override
    public void initProduct() {
     List<OrderProductQuantity> productQuantityList =new  ArrayList<OrderProductQuantity>();
    Category cat1 = new Category();
    
	cat1.setName("Pizzas");
    categoryDao.save(cat1);

     Category cat2 = new Category();
	
	 cat2.setName("Subs");
     categoryDao.save(cat2);


     Category cat3 = new Category();
	 
	 cat3.setName("Fried Rice");
     categoryDao.save(cat3);



     Category cat4 = new Category();
	
	 cat4.setName("Cakes");
     categoryDao.save(cat4);

        Product prd1 = new Product();
        // prd1.setId(1);
        prd1.setName("Chicken Alfredo");
        prd1.setCategory(cat1);
        prd1.setDescription("Butter, Garlic, Onion, Grilled shredded chicken, Heavy cream, Milk, Parmesan, Mozzarella, Olive oil, Ground black pepper or white pepper, Green Onions");
        prd1.setPrice(12);
        prd1.setStatus("true");
        productDao.save(prd1);
       
    

        Product prd2 = new Product();
        // prd2.setId(2);
        prd2.setName("Cheeseburger Style");
        prd2.setCategory(cat1);
        prd2.setDescription("Tomatoes, Cheese sauce, CheeseGround beef, Lettuce, Bacon, Pickles");
        prd2.setPrice(18);
        prd2.setStatus("true");
        productDao.save(prd2);

    }

    // Product prd3 = new Product();
    // prd3.setId(3);
    // prd3.setName("Chicken Alfredo");
    // prd3.setDescription(null);
    // prd3.setPrice(null);
    // prd3.setStatus("Instock");
    // productDao.save(prd3);


    // Product prd1 = new Product();
    // prd1.setId(1);
    // prd1.setName("Chicken Alfredo");
    // prd1.setDescription(null);
    // prd1.setPrice(null);
    // prd1.setStatus("Instock");
    // productDao.save(prd1);

}
