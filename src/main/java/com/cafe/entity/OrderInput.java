package com.cafe.entity;

import java.util.List;

public class OrderInput {


private String fullName;
private String fullAddress;
private String email;
private String contactNumber;
private String alternateContactNumber;  
private List<OrderProductQuantity>OrderProductQuantityList;



public String getFullName() {
    return fullName;
}
public void setFullName(String fullName) {
    this.fullName = fullName;
}
public String getFullAddress() {
    return fullAddress;
}
public void setFullAddress(String fullAddress) {
    this.fullAddress = fullAddress;
}
public String getContactNumber() {
    return contactNumber;
}
public void setContactNumber(String contactNumber) {
    this.contactNumber = contactNumber;
}
public String getAlternateContactNumber() {
    return alternateContactNumber;
}
public void setAlternateContactNumber(String alternateContactNumber) {
    this.alternateContactNumber = alternateContactNumber;
}
public List<OrderProductQuantity> getOrderProductQuantityList() {
    return OrderProductQuantityList;
}
public void setOrderProductQuantityList(List<OrderProductQuantity> orderProductQuantityList) {
    OrderProductQuantityList = orderProductQuantityList;
}
public String getEmail() {
    return email;
}
public void setEmail(String email) {
    this.email = email;
} 


    
}
