import { OrderQuantity } from "./order-quantity.model"

export interface OrderDetails {

    email: any
    fulllName: any
    fullAddress: any
    contactNumber: any
    alternateContactNumber: any  
    transactionId:any
    orderProductQuantityList :OrderQuantity[]
    

}