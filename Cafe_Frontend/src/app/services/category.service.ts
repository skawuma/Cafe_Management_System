import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {
  headers = new HttpHeaders({ 'No-Auth': 'True' });
  url = environment.apiUrl;

  constructor(private httpClient: HttpClient) { }

  add(category: Object): Observable<Object> {
    return this.httpClient.post(`${this.url}/category/add`, category);
  }

  // add1(data: any) {
  //   return this.httpClient.post(this.url +
  //     "/category/add", data, {
  //     headers: new HttpHeaders().set('Content-Type', "application/json")
  //   })
  // }

  update(category: Object): Observable<Object> {
    return this.httpClient.post(`${this.url}/category/update`, category);
  }

  update1(data: any) {
    return this.httpClient.post(this.url +
      "/category/update", data, {
      headers: new HttpHeaders({ 'No-Auth': 'True' }).set('Content-Type', "application/json")
    })
  }

  getCategorys() {
    return this.httpClient.get(this.url + "/category/get");
  }

  getFilteredCategorys() {
    return this.httpClient.get(this.url + "/category/get?filterValue=true");
  }
}