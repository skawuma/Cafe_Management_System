import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor,
  HttpErrorResponse
} from '@angular/common/http';
import { Observable, catchError, throwError } from 'rxjs';
import { Router } from '@angular/router';

@Injectable()
export class TokenInterceptorService implements HttpInterceptor {
  constructor(private router: Router) { }
  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const token = localStorage.getItem('token');

    if (token) {
      // If we have a token, we set it to the header
      request = request.clone({
        setHeaders: { Authorization: `Bearer ${token}` }
      });
    }

    return next.handle(request).pipe(
      catchError((err) => {
        if (err instanceof HttpErrorResponse) {
          console.log(err.url);
          if (err.status === 401 || err.status === 403) {
            if (this.router.url === '/') {
              //Do nothing
            }
            else {
              localStorage.clear();
              this.router.navigate(['/']);
              // window.location.reload();
            }
            // redirect user to the logout page
          }
        }
        return throwError(err);
      })
    )
  }
}
