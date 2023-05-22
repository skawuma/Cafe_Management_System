import { Injectable } from '@angular/core';
import { AuthService } from './auth.service';
import { ActivatedRouteSnapshot, CanActivate, Router } from '@angular/router';
import { SnachbarService } from './snachbar.service';
import { GlobalConstants } from 'src/shared/global-constants';
import jwt_decode from "jwt-decode";

@Injectable({
  providedIn: 'root'
})
export class RouteGuardService implements CanActivate {
  constructor(public auth: AuthService, public router: Router, private snachbarService: SnachbarService) { }
  canActivate(route: ActivatedRouteSnapshot): boolean {

    let expectedRoleArray:any = route.data;
    expectedRoleArray = expectedRoleArray.expectedRole;

    //const token: any = localStorage.getItem('jwtToken')|| 'null' ;

    const token: any = localStorage.getItem('token')|| 'null';

    // decode the token to get its payload
    var tokenPayload: any;
    try {
      tokenPayload = jwt_decode(token);
      
    }
    catch (err) {
      localStorage.clear();
      this.router.navigate(['/']);
    }

    let expectedRole = '';

    for (let i = 0; i < expectedRoleArray.length; i++) {
      if (expectedRoleArray[i] == tokenPayload.role) {
        expectedRole = tokenPayload.role;
      }
    }

    if (tokenPayload.role == 'User' || tokenPayload.role == 'Admin') {
      if (this.auth.isAuthenticated() && tokenPayload.role == expectedRole) {
        return true;
      }
      this.snachbarService.openSnackBar(GlobalConstants.unauthroized, GlobalConstants.error);
      this.router.navigate(['/cafe/dashboard']);
      return false;
    }
    else {
      this.router.navigate(['/']);
      localStorage.clear();
      return false;
    }
  }
  

}

