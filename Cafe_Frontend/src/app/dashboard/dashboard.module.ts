import { CommonModule } from "@angular/common";
import { NgModule } from "@angular/core";
import { MaterialModule } from "src/shared/material-module";
;
import { RouterModule } from "@angular/router";
import { DashboardComponent } from "./dashboard.component";
import { DashboardRoutes } from "./dashboard.routing";


@NgModule({
    imports: [
      CommonModule,
     MaterialModule,
    //FlexLayoutModule,
       RouterModule.forChild(DashboardRoutes)
    ],
    declarations: [DashboardComponent]
  })
  export class DashboardModule { }