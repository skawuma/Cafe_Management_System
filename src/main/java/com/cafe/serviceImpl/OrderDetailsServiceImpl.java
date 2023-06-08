package com.cafe.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe.dao.OrderDetailsDao;
import com.cafe.dao.ProductDao;
import com.cafe.dao.UserDao;
import com.cafe.entity.OrderDetails;
import com.cafe.entity.OrderInput;
import com.cafe.entity.OrderProductQuantity;
import com.cafe.entity.Product;
import com.cafe.entity.User;
import com.cafe.jwt.JwtFilter;
import com.cafe.service.OrderDetailsService;
import com.cafe.service.ProductService;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
public class OrderDetailsServiceImpl implements OrderDetailsService{

private static final  String ORDER_PLACED ="Placed";
private static final  String PAY_TYPE ="Credit";

@Autowired
UserDao userDao;  

 @Autowired
OrderDetailsDao orderDetailsDao;

@Autowired
private ProductDao productDao;

@Autowired
	JwtFilter jwtFilter;

    
    @Override
    public void placeOrder(OrderInput orderInput) {
    List<OrderProductQuantity> productQuantityList = orderInput.getOrderProductQuantityList();

   for (OrderProductQuantity o: productQuantityList){

    Product product =productDao.findById(o.getProductId()).get();

     String currentUser = jwtFilter.getCurrentUser();   

     User user =  userDao.findByEmail(currentUser);  

    OrderDetails orderDetail = new OrderDetails(orderInput.getEmail(), orderInput.getFullName(), orderInput.getContactNumber(), orderInput.getContactNumber(), orderInput.getAlternateContactNumber(),  ORDER_PLACED, product.getPrice()*o.getQuantity() , product, user


    );

    orderDetailsDao.save(orderDetail);


   }
 

    }
    
    
    


    
}
