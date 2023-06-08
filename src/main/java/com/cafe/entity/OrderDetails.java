package com.cafe.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class OrderDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderId;
    private String orderEmail;
    private String orderFullName;
    private String orderFullAddress; 
    private String orderContactNumber;
    private String orderAlternateContactNumber;
    private String orderStatus;
    private Integer orderAmount;
    private String paymentMethod;

    @OneToOne
    private Product product;

    @OneToOne
    private User user;




   



    







   




    public OrderDetails(String orderEmail, String orderFullName, String orderFullAddress, String orderContactNumber,
            String orderAlternateContactNumber, String orderStatus, Integer orderAmount, Product product, User user) {
        this.orderEmail = orderEmail;
        this.orderFullName = orderFullName;
        this.orderFullAddress = orderFullAddress;
        this.orderContactNumber = orderContactNumber;
        this.orderAlternateContactNumber = orderAlternateContactNumber;
        this.orderStatus = orderStatus;
        this.orderAmount = orderAmount;
        this.product = product;
        this.user = user;
    }




    public Product getProduct() {
        return product;
    }




    public void setProduct(Product product) {
        this.product = product;
    }




    public User getUser() {
        return user;
    }




    public void setUser(User user) {
        this.user = user;
    }




    public Integer getOrderId() {
        return orderId;
    }


    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }


    public String getOrderFullName() {
        return orderFullName;
    }


    public void setOrderFullName(String orderFullName) {
        this.orderFullName = orderFullName;
    }


    public String getOrderFullAddress() {
        return orderFullAddress;
    }


    public void setOrderFullAddress(String orderFullAddress) {
        this.orderFullAddress = orderFullAddress;
    }


    public String getOrderContactNumber() {
        return orderContactNumber;
    }


    public void setOrderContactNumber(String orderContactNumber) {
        this.orderContactNumber = orderContactNumber;
    }


    public String getOrderAlternateContactNumber() {
        return orderAlternateContactNumber;
    }


    public void setOrderAlternateContactNumber(String orderAlternateContactNumber) {
        this.orderAlternateContactNumber = orderAlternateContactNumber;
    }


    public String getOrderStatus() {
        return orderStatus;
    }


    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }


    public Integer getOrderAmount() {
        return orderAmount;
    }


    public void setOrderAmount(Integer orderAmount) {
        this.orderAmount = orderAmount;
    }


    public String getPaymentMethod() {
        return paymentMethod;
    }


    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }




    public String getOrderEmail() {
        return orderEmail;
    }




    public void setOrderEmail(String orderEmail) {
        this.orderEmail = orderEmail;
    } 

    
    
    
}
