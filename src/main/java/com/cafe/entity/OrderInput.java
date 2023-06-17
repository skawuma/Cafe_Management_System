package com.cafe.entity;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;





public class OrderInput {

private String email;
private String fullName;
private String fullAddress;
private String contactNumber;
private String alternateContactNumber; 
private String transactionId;

private List<OrderProductQuantity>orderProductQuantityList;



// @Override
// public String toString() {
//     return "OrderInput [email=" + email + ", fullName=" + fullName + ", fullAddress=" + fullAddress + ", contactNumber="
//             + contactNumber + ", alternateContactNumber=" + alternateContactNumber + ", OrderProductQuantityList="
//             + OrderProductQuantityList + "]";
// }
// public OrderInput() {
// }
// public OrderInput(String email, String fullName, String fullAddress, String contactNumber,
//         String alternateContactNumber, List<OrderProductQuantity> orderProductQuantityList) {
//     this.email = email;
//     this.fullName = fullName;
//     this.fullAddress = fullAddress;
//     this.contactNumber = contactNumber;
//     this.alternateContactNumber = alternateContactNumber;
//     this.OrderProductQuantityList = orderProductQuantityList;
// }
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
    return orderProductQuantityList;
}

public void setOrderProductQuantityList(List<OrderProductQuantity> orderProductQuantityList) {
   this.orderProductQuantityList = orderProductQuantityList;
}
public String getEmail() {
    return email;
}
public void setEmail(String email) {
    this.email = email;
}
public String getTransactionId() {
    return transactionId;
}
public void setTransactionId(String transactionId) {
    this.transactionId = transactionId;
} 


    
}
