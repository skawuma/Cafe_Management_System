import { Routes } from "@angular/router";
import { DashboardComponent } from "../dashboard/dashboard.component";
import { RouteGuardService } from "../services/route-guard.service";
import { ManageCategoryComponent } from "./manage-category/manage-category.component";
import { ManageOrderComponent } from "./manage-order/manage-order.component";
import { ManageProductComponent } from "./manage-product/manage-product.component";
import { ManageUserComponent } from "./manage-user/manage-user.component";
import { ViewBillComponent } from "./view-bill/view-bill.component";

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