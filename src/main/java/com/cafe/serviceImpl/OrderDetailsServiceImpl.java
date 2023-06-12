package com.cafe.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
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
import com.cafe.wrapper.ProductWrapper;

import lombok.extern.slf4j.Slf4j;


@ComponentScan
@Service
public class OrderDetailsServiceImpl implements OrderDetailsService{

private static final  String ORDER_PLACED ="Placed";
// private static final  String PAY_TYPE ="Credit";

@Autowired
UserDao userDao;  

 @Autowired
OrderDetailsDao orderDetailsDao;

@Autowired
ProductDao productDao;

@Autowired
JwtFilter jwtFilter;

    @Override
    public void placeOrder(OrderInput orderInput) {
   // OrderInput orderInput1=new OrderInput();

  
   List<OrderProductQuantity> productQuantityList= new ArrayList<>();
   
    productQuantityList = orderInput.getOrderProductQuantityList();
  
//   //  orderInput1.setOrderProductQuantityList((orderInput.getOrderProductQuantityList()));
//     OrderProductQuantity ord = new OrderProductQuantity();
//     OrderProductQuantity ord1 = new OrderProductQuantity();
//     ord.setId(ord.getId());
//     ord1.setQuantity(ord1.getQuantity());
//   //  ord.setQuantity(ord.getQuantity());
 
//    productQuantityList.add(ord);
//    productQuantityList.add(ord1);
//    for(int i= 0; i < productQuantityList.size(); i++) {
//     System.out.print(productQuantityList.get(i) + " ");
//  }
   for (OrderProductQuantity o:productQuantityList){ 
    Product product = productDao.findById(o.getId()).get();

     //Product product = productDao.findById(o.getId()).get();

      String currentUser = jwtFilter.CURRENT_USER; 
    
     User user = userDao.findByEmailId1(currentUser).get();
       
       OrderDetails orderDetails = new OrderDetails(
     
       orderInput.getEmail(),
       orderInput.getFullName(),  
       orderInput.getFullAddress() , 
       orderInput.getContactNumber(),
       orderInput.getAlternateContactNumber(), 
       ORDER_PLACED,
       product.getPrice()*  o.getQuantity(),

        product, user); 
    orderDetailsDao.save( orderDetails);

   }

    }

    @Override
    public Iterable<OrderDetails> displayAllOrderDetails() {
        // TODO Auto-generated method stub
        return orderDetailsDao.findAll();
    }
    
}
