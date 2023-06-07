import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, NgForm, Validators } from '@angular/forms';
import * as saveAs from 'file-saver';
import { NgxUiLoaderService } from 'ngx-ui-loader';
//import * as Razorpay from 'razorpay';


// import * as Razorpay from 'razorpay';
import { BillService } from 'src/app/services/bill.service';
import { CategoryService } from 'src/app/services/category.service';
import { ProductService } from 'src/app/services/product.service';
import { SnachbarService } from 'src/app/services/snachbar.service';
import { GlobalConstants } from 'src/shared/global-constants';

declare var  Razorpay:any;
@Component({
  selector: 'app-manage-order',
  templateUrl: './manage-order.component.html',
  styleUrls: ['./manage-order.component.css']
})
export class ManageOrderComponent implements OnInit {

  displayedColumns: string[] = ['name', 'category', 'price', 'quantity', 'total', 'edit'];
  dataSource: any = [];
  manageOrderForm: any = FormGroup;
  categorys: any = [];
  products: any = [];
  price: any;
  totalAmount1: any;
  totalAmount: number = 0;
  transactionId: any; 
  responseMessage: any;

  constructor(private formBuilder: FormBuilder,
    private categoryService: CategoryService,
    private productService: ProductService,
    private snachbarService: SnachbarService,
    private billService: BillService,
    private ngxService: NgxUiLoaderService) { }

  ngOnInit() {
    this.ngxService.start();
    this.getCategorys();
    this.manageOrderForm = this.formBuilder.group({
      name: [null, [Validators.required, Validators.pattern(GlobalConstants.nameRegex)]],
      email: [null, [Validators.required, Validators.pattern(GlobalConstants.emailRegex)]],
      contactNumber: [null, [Validators.required, Validators.pattern(GlobalConstants.contactNumberRegex)]],
      paymentMethod: [null, [Validators.required]],
      product: [null, [Validators.required]],
      category: [null, [Validators.required]],
      quantity: [null, [Validators.required]],
      price: [null, [Validators.required]],
      total: [0, [Validators.required]]
    });
  }

  getCategorys() {
    this.categoryService.getFilteredCategorys().subscribe((response: any) => {
      this.ngxService.stop();
      this.categorys = response;
    }, (error: any) => {
      this.ngxService.stop();
      console.log(error.error?.message);
      if (error.error?.message) {
        this.responseMessage = error.error?.message;
      }
      else {
        this.responseMessage = GlobalConstants.genericError;
      }
      this.snachbarService.openSnackBar(this.responseMessage, GlobalConstants.error);
    })
  }

  getCategory(value: any) {
    this.productService.getByCategory(value.id).subscribe((response: any) => {
      this.products = response;
      this.manageOrderForm.controls['price'].setValue('');
      this.manageOrderForm.controls['quantity'].setValue('');
      this.manageOrderForm.controls['total'].setValue(0);
    }, (error: any) => {
      console.log(error.error?.message);
      if (error.error?.message) {
        this.responseMessage = error.error?.message;
      }
      else {
        this.responseMessage = GlobalConstants.genericError;
      }
      this.snachbarService.openSnackBar(this.responseMessage, GlobalConstants.error);
    })
  }

  getProductDetails(value: any) {
    this.productService.getById(value.id).subscribe((response: any) => {
      this.price = response.price;
      this.manageOrderForm.controls['price'].setValue(response.price);
      this.manageOrderForm.controls['quantity'].setValue('1');
      this.manageOrderForm.controls['total'].setValue(this.price * 1);
    }, (error: any) => {
      console.log(error.error?.message);
      if (error.error?.message) {
        this.responseMessage = error.error?.message;
      }
      else {
        this.responseMessage = GlobalConstants.genericError;
      }
      this.snachbarService.openSnackBar(this.responseMessage, GlobalConstants.error);
    })
  }

  setQuantity(value: any) {
    var temp = this.manageOrderForm.controls['quantity'].value;
    if (temp > 0) {
      this.manageOrderForm.controls['total'].setValue(this.manageOrderForm.controls['quantity'].value * this.manageOrderForm.controls['price'].value);
    }
    else if (temp != '') {
      this.manageOrderForm.controls['quantity'].setValue('1');
      this.manageOrderForm.controls['total'].setValue(this.manageOrderForm.controls['quantity'].value * this.manageOrderForm.controls['price'].value);
    }
  }

  validateProductAdd() {
    if (this.manageOrderForm.controls['total'].value === 0 || this.manageOrderForm.controls['total'].value === null || this.manageOrderForm.controls['quantity'].value <= 0) {
      return true;
    }
    else
      return false;
  }

  validateSubmit() {
    if (this.totalAmount === 0 || this.manageOrderForm.controls['name'].value === null || this.manageOrderForm.controls['email'].value === null || this.manageOrderForm.controls['contactNumber'].value === null || this.manageOrderForm.controls['paymentMethod'].value === null) {
      return true;
    }
    else
      return false;
  }


  public  placeOrder(manageOrderForm: NgForm)
  
  {
    var formData = this.manageOrderForm.value;
    var productName = this.dataSource.find((e: { id: number; }) => e.id === formData.product.id);
    if (productName === undefined) {
      this.totalAmount = this.totalAmount + formData.total;
      this.dataSource.push({ id: formData.product.id, name: formData.product.name, category: formData.category.name, quantity: formData.quantity, price: formData.price, total: formData.total });
      this.dataSource = [...this.dataSource];
      this.snachbarService.openSnackBar(GlobalConstants.productAdded, "success");
    }
    else {
      this.snachbarService.openSnackBar(GlobalConstants.productExistError, GlobalConstants.error);
    }
  }

  add()
  
  {
    var formData = this.manageOrderForm.value;
    var productName = this.dataSource.find((e: { id: number; }) => e.id === formData.product.id);
    if (productName === undefined) {
      this.totalAmount = this.totalAmount + formData.total;
      this.dataSource.push({ id: formData.product.id, name: formData.product.name, category: formData.category.name, quantity: formData.quantity, price: formData.price, total: formData.total });
      this.dataSource = [...this.dataSource];
      this.snachbarService.openSnackBar(GlobalConstants.productAdded, "success");
    }
    else {
      this.snachbarService.openSnackBar(GlobalConstants.productExistError, GlobalConstants.error);
    }
  }

  handleDeleteAction(value: any, element: any) {
    this.totalAmount = this.totalAmount - element.total;
    this.dataSource.splice(value, 1);
    this.dataSource = [...this.dataSource];
  }
 
  submitAction() {
    var formData = this.manageOrderForm.value;
    var data = {
      name: formData.name,
      email: formData.email,
      contactNumber: formData.contactNumber,
      paymentMethod: formData.paymentMethod,
      totalAmount: this.totalAmount.toString(),
      productDetails: JSON.stringify(this.dataSource)
    }
    this.ngxService.start();
    this.billService.generateReport(data).subscribe((response: any) => {
      this.downloadFile(response?.uuid);
      this.manageOrderForm.reset();
      this.dataSource = [];
      this.totalAmount = 0;
    }, (error: any) => {
      this.ngxService.stop();
      console.log(error);
      if (error.error?.message) {
        this.responseMessage = error.error?.message;
      }
      else
        this.responseMessage = GlobalConstants.genericError;
      this.snachbarService.openSnackBar(this.responseMessage, GlobalConstants.error);
    })
  }

  public downloadFile(fileName: string) {
    var data = {
      uuid: fileName
    }
    this.productService.getPDF(data).subscribe(
      (res) => {
        saveAs(res, fileName + '.pdf');
        this.ngxService.stop();
      }
    );
  } 


  createTransactionAndPlaceOrder( manageOrderForm: NgForm){
    var formData = this.manageOrderForm.value;
   // this.totalAmount = this.totalAmount + formData.total;
    //this.totalAmount = this.totalAmount;
   //let amount = this.totalAmount.toString();
   let amount =  formData.total;

    this.productService.createTransaction(amount).subscribe(
      (response)=>{
        console.log(response);
        this.openTransactionModal(response,manageOrderForm);
      },
    (error) => {
      console.log(error);
    }
    )

  }

openTransactionModal(response: any, manageOrderForm: NgForm){
  var formData = this.manageOrderForm.value;
  var options ={
order_id: response.order_id,
key: response.key,
amount: response.amount,
currency: response.currency,
name: formData.name,
description:'payment made to',



config: {
  display: {
    blocks: {
      banks: {
        name: 'All payment methods',
        instruments: [
          {
            method: 'upi'
          },
          {
            method: 'card'
          },

          {
            method: 'wallet',
            wallets: ['freecharge']
          },

          {
              method: 'wallet',
              wallets: ['olamoney']
          },
          {
              method: 'netbanking'
          }
        ],
      },
    },
    sequence: ['block.banks'],
    preferences: {
      show_default_blocks: true,
    },
  },
},
// image:'',
handler: (response:any) =>{
  if (response!=null && response.razor_payment_id !=null){


    this.processResponse(response,manageOrderForm);
  }
  else{

    alert("payment failed..")
  }

},

prefill:{
  name: formData.name,
  email: formData.email,
  contactNumber: formData.contactNumber,
  // paymentMethod: formData.paymentMethod,
  // totalAmount: this.totalAmount.toString(),
  // productDetails: JSON.stringify(this.dataSource)

},



notes:{

address:'Stellas Confectionery'

},
theme:{
  color:'#F37254'
},


  };



  var razorPayObject = new Razorpay(options);
  razorPayObject.open();
}

processResponse(resp:any,manageOrderForm: NgForm){
//console.log(resp);
this.transactionId= resp.razor_payment_id;
this.placeOrder(manageOrderForm);
}




}

  // <form [formGroup]="manageOrderForm" (ngSubmit)="createTransactionAndPlaceOrder(manageOrderForm)">

//   <div align = "right">
//   <button type=" submit" mat-stroked-button color = "primary">Place Order </button>
// </div>




  // public showPDF(fileName: string): void {
  //   this.productService.getPDF(fileName, '')
  //     .subscribe(x => {
  //       this.ngxService.stop();
  //       // It is necessary to create a new blob object with mime-type explicitly set
  //       // otherwise only Chrome works like it should
  //       var newBlob = new Blob([x], { type: "application/pdf" });

  //       // IE doesn't allow using a blob object directly as link href
  //       // instead it is necessary to use msSaveOrOpenBlob
  //       if (window.navigator && window.navigator.msSaveOrOpenBlob) {
  //         window.navigator.msSaveOrOpenBlob(newBlob, fileName);
  //         return;
  //       }

  //       // For other browsers: 
  //       // Create a link pointing to the ObjectURL containing the blob.
  //       const data = window.URL.createObjectURL(newBlob);

  //       var link = document.createElement('a');
  //       link.href = data;
  //       link.download = fileName;
  //       // this is necessary as link.click() does not work on the latest firefox
  //       link.dispatchEvent(new MouseEvent('click', { bubbles: true, cancelable: true, view: window }));

  //       setTimeout(function () {
  //         // For Firefox it is necessary to delay revoking the ObjectURL
  //         window.URL.revokeObjectURL(data);
  //         link.remove();
  //       }, 100);
  //     });
  // }


