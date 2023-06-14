import { CommonModule } from "@angular/common";
import { RouterModule } from "@angular/router";
import { MaterialModule } from "src/shared/material-module";
import { MaterialRoutes } from "./material.routing";
import { HttpClientModule } from "@angular/common/http";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { CdkTableModule } from "@angular/cdk/table";
import { ProductComponent } from "./dialog/product/product.component";
import { ManageProductComponent } from "./manage-product/manage-product.component";
import { ManageOrderComponent } from "./manage-order/manage-order.component";
import { ManageCategoryComponent } from "./manage-category/manage-category.component";
import { CategoryComponent } from "./dialog/category/category.component";
import { ViewBillComponent } from "./view-bill/view-bill.component";
import { ViewBillProductsComponent } from "./dialog/view-bill-products/view-bill-products.component";
import { ManageUserComponent } from "./manage-user/manage-user.component";
import { ConfirmationComponent } from "./dialog/confirmation/confirmation.component";
import { NgModule } from "@angular/core";
import { BuyProductComponent } from './buy-product/buy-product.component';
import { ProductViewDetailsComponent } from './product-view-details/product-view-details.component';
import { ShowProductDetailsComponent } from './show-product-details/show-product-details.component';
import { ShowProductImagesDialogComponent } from './show-product-images-dialog/show-product-images-dialog.component';
//import { FlexLayoutModule } from "@angular/flex-layout";


@NgModule({
    imports: [
      CommonModule,
      RouterModule.forChild(MaterialRoutes),
      MaterialModule,
      HttpClientModule,
      FormsModule,
      ReactiveFormsModule,
     // FlexLayoutModule,
      CdkTableModule
    ],
    providers: [],
    declarations: [
      ManageProductComponent,
      ProductComponent,
      ManageOrderComponent,
      ManageCategoryComponent,
      CategoryComponent,
      ViewBillComponent,
      ViewBillProductsComponent,
      ManageUserComponent,
      ConfirmationComponent,
      BuyProductComponent,
      ProductViewDetailsComponent,
      ShowProductDetailsComponent,
      ShowProductImagesDialogComponent,
      
    ]
  })
  export class MaterialComponentsModule {}