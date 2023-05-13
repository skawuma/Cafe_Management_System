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
    
    MaterialModule,
    SharedModule,
    NgxUiLoaderModule.forRoot(ngxUiLoaderConfig)



  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
