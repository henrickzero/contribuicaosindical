import { Router } from '@angular/router';
import { tap } from 'rxjs/operators';
import { AuthConstants } from './auth-constants';
import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpHandler, HttpRequest, HttpEvent, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment as env } from 'src/environments/environment';

@Injectable()
export class TokenInterceptor implements HttpInterceptor {

  constructor(private router: Router) { }

  // eslint-disable-next-line @typescript-eslint/no-explicit-any
  public intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const authToken = sessionStorage.getItem(AuthConstants.TOKEN);
    const subsidiary1 = sessionStorage.getItem(AuthConstants.SUBSIDIARY);
    let accessToken = '';
    let subsidiary = '';

    if (authToken != null) {
      //const loginModelStorage = JSON.parse(authToken);
      accessToken = authToken;
    }
    if (subsidiary1 != null) {
      subsidiary = subsidiary1;
    }

    const requestAtt = req.clone({
      headers: req.headers.set('X-API-KEY', accessToken)
                          .set('X-API-SUBSIDIARY', subsidiary)
    });

    return next.handle(requestAtt).pipe( tap(() => undefined,
      // eslint-disable-next-line @typescript-eslint/no-explicit-any
      (err: any) => {
      if (err instanceof HttpErrorResponse) {
        if (err.status !== 401 && err.status !== 403) {
         return;
        }
        this.router.navigate(['/login']);
      }
    }));

  }


  
}
