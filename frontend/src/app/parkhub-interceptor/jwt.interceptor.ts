import {HttpErrorResponse, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest, HttpResponse} from '@angular/common/http';
import {LoginService} from '../service/login.service';
import {Observable} from 'rxjs';
import {Router} from '@angular/router';
import 'rxjs/add/operator/do';

export class JwtInterceptor implements HttpInterceptor {
  constructor(public login: LoginService,
              private router: Router) {}


  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

    return next.handle(request).do((event: HttpEvent<any>) => {
      if (event instanceof HttpResponse) {
        this.router.navigate(['/home']);
      }
    }, (err: any) => {
      if (err instanceof HttpErrorResponse) {
        if (err.status === 401) {
          this.router.navigate(['/login']);
        }
      }
    });
  }
}
