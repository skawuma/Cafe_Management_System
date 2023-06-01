import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  headers = new HttpHeaders({ 'No-Auth': 'True' });
  url = environment.apiUrl;
  constructor(private httpClient:HttpClient) { }

  signup(data: any) {
    return this.httpClient.post(this.url +
      "/user/signup", data, {
      headers: new HttpHeaders().set('Content-Type', "application/json")
    })
  }

  forgotPassword(data: any) {
    return this.httpClient.post(this.url +
      "/user/forgotPassword", data, {
      headers: new HttpHeaders().set('Content-Type', "application/json")
    })
  }

  login(data: any) {
    return this.httpClient.post(this.url +
      "/user/login", data, {
      headers: new HttpHeaders().set('Content-Type', "application/json")
    })
  }

  checkToken() {
    return this.httpClient.get(this.url + "/user/checkToken");
  }

  getUsers() {
    return this.httpClient.get(this.url + "/user/get");
  }

  update(user: Object): Observable<Object> {
    return this.httpClient.post(`${this.url}/user/update`, user);
  }

  update1(data: any) {
    return this.httpClient.post(this.url +
      "/user/update", data, {
      headers: new HttpHeaders().set('Content-Type', "application/json")
    })
  }

  changePassword1(data: any) {
    return this.httpClient.post(this.url +
      "/user/changePassword/", data, {
      headers: new HttpHeaders().set('Content-Type', "application/json")
    })
  } 
//Updated fuction for Change password
  changePassword(user: object): Observable<Object>{
    return this.httpClient.post(`${this.url}/user/changePassword`, user);
  }

}
