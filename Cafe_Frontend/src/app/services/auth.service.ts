import { Injectable } from '@angular/core';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthService {


  constructor(private router: Router) { }

  public isAuthenticated(): boolean {
    const token = localStorage.getItem('token');
   //const token=localStorage.getItem('jwtToken')|| 'null' ;
    if (!token) {
       console.log("Token does not exists");

      this.router.navigate(['/']);
     
      return false;
    }
    else {
       console.log("Token exists");
      return true;
    }
  }
}
