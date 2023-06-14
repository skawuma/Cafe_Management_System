import { Routes } from "@angular/router";
import { DashboardComponent } from "../dashboard/dashboard.component";
import { RouteGuardService } from "../services/route-guard.service";
import { ManageCategoryComponent } from "./manage-category/manage-category.component";
import { ManageOrderComponent } from "./manage-order/manage-order.component";
import { ManageProductComponent } from "./manage-product/manage-product.component";
import { ManageUserComponent } from "./manage-user/manage-user.component";
import { ViewBillComponent } from "./view-bill/view-bill.component";
import { BuyProductComponent } from "./buy-product/buy-product.component";
import { ProductViewDetailsComponent } from "./product-view-details/product-view-details.component";
import { ShowProductDetailsComponent } from "./show-product-details/show-product-details.component";
import { ShowProductImagesDialogComponent } from "./show-product-images-dialog/show-product-images-dialog.component";

export const MaterialRoutes: Routes = [
    {
      path: 'category',
      component: ManageCategoryComponent,
      canActivate: [RouteGuardService],
      data: {
        expectedRole: ['Admin', 'User']
       // expectedRole: ['Admin']
      }
    },
    {
      path: 'product',
      component: ManageProductComponent,
      canActivate: [RouteGuardService],
      data: {
       // expectedRole: ['Admin']
       expectedRole: ['Admin', 'User']
      }
    },

    {
      path: 'buyproduct',
      component: BuyProductComponent,
      canActivate: [RouteGuardService],
      data: {
       // expectedRole: ['Admin']
       expectedRole: [ 'User']
      }
    },

    {
      path: 'productviewdetails',
      component: ProductViewDetailsComponent,
      canActivate: [RouteGuardService],
      data: {
       // expectedRole: ['Admin']
       expectedRole: [ 'User']
      }
    },
    {
      path: 'showproductdetails',
      component: ShowProductDetailsComponent,
      canActivate: [RouteGuardService],
      data: {
       // expectedRole: ['Admin']
       expectedRole: [ 'User']
      }
    },
    {
      path: 'showproductimages',
      component: ShowProductImagesDialogComponent,
      canActivate: [RouteGuardService],
      data: {
       // expectedRole: ['Admin']
       expectedRole: [ 'User']
      }
    },

    {
      path: 'order',
      component: ManageOrderComponent,
      canActivate: [RouteGuardService],
      data: {
        expectedRole: ['Admin', 'User']
      }
    },
    {
      path: 'bill',
      component: ViewBillComponent,
      canActivate: [RouteGuardService],
      data: {
        expectedRole: ['Admin', 'User']
      }
    },
    {
      path: 'user',
      component: ManageUserComponent,
      canActivate: [RouteGuardService],
      data: {
        expectedRole: ['Admin']
      }
    }
    ,
    { path: '**', component: DashboardComponent }
  ];