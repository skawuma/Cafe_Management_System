import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-view-bill-products',
  templateUrl: './view-bill-products.component.html',
  styleUrls: ['./view-bill-products.component.css']
})
export class ViewBillProductsComponent implements OnInit {
  displayedColumns: string[] = ['name','category', 'price', 'quantity','total'];
  dataSource: any;
  name:any;
  email:any;
  contactNumber:any;
  paymentMethod:any;
  constructor(@Inject(MAT_DIALOG_DATA) public dialogData: any,
    public dialogRef: MatDialogRef<ViewBillProductsComponent>
  ) { }

  ngOnInit() {
    this.name = this.dialogData.data.name;
    this.email = this.dialogData.data.email;
    this.contactNumber = this.dialogData.data.contactNumber;
    this.paymentMethod = this.dialogData.data.paymentMethod;
    this.dataSource = JSON.parse(this.dialogData.data.productDetail);
  }
}
