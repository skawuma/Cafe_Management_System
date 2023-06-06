import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ProductService {
  url = environment.apiUrl;

  constructor(private httpClient: HttpClient) { }

  add(data: any) {
    return this.httpClient.post(this.url +
      "/product/add", data, {
      headers: new HttpHeaders().set('Content-Type', "application/json")
    })
  }

  update(data: any) {
    return this.httpClient.post(this.url +
      "/product/update", data, {
      headers: new HttpHeaders().set('Content-Type', "application/json")
    })
  }
 
 public  createTransaction(amount: any){
    return this.httpClient.get(this.url + "/category/createTransaction/" + amount);
    ///createTransaction/{amount}
  }

  // createTransaction3(amount:any): Observable<any> {
  //   return this.httpClient.get(`${this.url}/category/createTransaction/${amount}`);
  // }

  // createTransaction2(amount: any): Observable<any> {
  //   return this.httpClient.get(`${this.url}/category/createTransaction/`, amount);
  // }
  // createTransaction4(amount:any): Observable<any> {
  //   return this.httpClient.get(`${this.url}/category/createTransaction/${amount}`, { responseType: 'text' });
  // }

  getProducts() {
    return this.httpClient.get(this.url + "/product/get");
  }

  getByCategory(id: any) {
    return this.httpClient.get(this.url + "/product/getByCategory/" + id);
  }

  getById(id: any) {
    return this.httpClient.get(this.url + "/product/getById/" + id);
  }

  updateStatus(data: any) {
    return this.httpClient.post(this.url +
      "/product/updateStatus", data, {
      headers: new HttpHeaders().set('Content-Type', "application/json")
    })
  }

  delete(id: any) {
    return this.httpClient.post(this.url +
      "/product/delete/" + id, {
      headers: new HttpHeaders().set('Content-Type', "application/json")
    })
  }

  public getPDF(data: any): Observable<Blob> {
    //const options = { responseType: 'blob' }; there is no use of this
    let uri = this.url + "/bill/getPdf";
    // this.http refers to HttpClient. Note here that you cannot use the generic get<Blob> as it does not compile: instead you "choose" the appropriate API in this way.
    return this.httpClient.post(uri, data, { responseType: 'blob' });
  }
}