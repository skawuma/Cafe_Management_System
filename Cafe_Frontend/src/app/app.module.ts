import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {trigger,state,style,animate,transition}from '@angular/animations';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { MaterialModule } from 'src/shared/material-module';
import { SharedModule } from 'src/shared/shared.module';
import { BestSellerComponent } from './best-seller/best-seller.component';
import { HomeComponent } from './home/home.component';
import { SignupComponent } from './signup/signup.component';
import { NgxUiLoaderConfig, NgxUiLoaderModule, SPINNER } from 'ngx-ui-loader';
import { ForgotPasswordComponent } from './forgot-password/forgot-password.component';
import { LoginComponent } from './login/login.component';
import { FullComponent } from './layouts/full/full.component';
import { LayoutModule } from '@angular/cdk/layout';
import { TokenInterceptorService } from './services/token-interceptor.interceptor';
import { ChangePasswordComponent } from './material-component/dialog/change-password/change-password.component';
import { AppHeaderComponent } from './layouts/full/header/header.component';
import { AppSidebarComponent } from './layouts/full/sidebar/sidebar.component';
import { MatButtonModule } from '@angular/material/button';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatListModule } from '@angular/material/list';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MenuItems } from 'src/shared/menu-items';

//import { FlexLayoutModule } from '@angular/flex-layout';




const ngxUiLoaderConfig: NgxUiLoaderConfig = {
  text: "Loading...",
  textColor: "#FFFFFF",
  textPosition: "center-center",
  bgsColor: "#7b1fa2",
  fgsColor: "#7b1fa2",
  fgsType: SPINNER.threeStrings, // foreground spinner type
  fgsSize: 100,
  hasProgressBar: false
};

@NgModule({
  declarations: [
    AppComponent,
    BestSellerComponent,
    HomeComponent,
    SignupComponent,
    ChangePasswordComponent,
    AppHeaderComponent,
    AppSidebarComponent,
    ForgotPasswordComponent,
    LoginComponent,
    FullComponent
    
   
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    MatToolbarModule,
MatButtonModule,
MatCheckboxModule,
MatListModule,

  
    MaterialModule,
    SharedModule,
    NgxUiLoaderModule.forRoot(ngxUiLoaderConfig)



  ],
  providers: [HttpClientModule, { provide: HTTP_INTERCEPTORS, useClass: TokenInterceptorService, multi: true }],
  bootstrap: [AppComponent]
})
export class AppModule { }
