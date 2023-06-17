package com.cafe.entity;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "OrderDetails")
public class OrderDetails implements Serializable {

    private static final long serialVersionUID = 123456L;

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

    @OneToOne
    @JoinColumn(name ="product_id", referencedColumnName ="id")
    private Product product;

    @OneToOne
    @JsonIgnore
    @JoinColumn(name ="user_id", referencedColumnName ="id")
    private User user;


    
   private String transactionId;


    public OrderDetails(String orderEmail, String orderFullName, String orderFullAddress, String orderContactNumber,
        String orderAlternateContactNumber, String orderStatus, Integer orderAmount, Product product, User user,
        String transactionId) {
    this.orderEmail = orderEmail;
    this.orderFullName = orderFullName;
    this.orderFullAddress = orderFullAddress;
    this.orderContactNumber = orderContactNumber;
    this.orderAlternateContactNumber = orderAlternateContactNumber;
    this.orderStatus = orderStatus;
    this.orderAmount = orderAmount;
    this.product = product;
    this.user = user;
    this.transactionId = transactionId;
}





    public OrderDetails() {
    }





    public String getTransactionId() {
        return transactionId;
    }
    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
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


    




    public String getOrderEmail() {
        return orderEmail;
    }




    public void setOrderEmail(String orderEmail) {
        this.orderEmail = orderEmail;
    }








    public static long getSerialversionuid() {
        return serialVersionUID;
    } 

    
    
    
}
