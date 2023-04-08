package com.cafe.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import com.cafe.entity.Product;
import com.cafe.wrapper.ProductWrapper;
import jakarta.transaction.Transactional;

/*
samuelkawuma
3:27:58 PM
Apr 8, 2023
*/
public interface ProductDao extends JpaRepository<Product,Integer> {
	
	List<ProductWrapper> getAllProduct();

    @Modifying
    @Transactional
    Integer updateProductStatus(@Param("status") String status, @Param("id") Integer id);

    List<ProductWrapper> getProductByCategory(@Param("id") Integer id);

    ProductWrapper getProductById(@Param("id") Integer id);
	

}
