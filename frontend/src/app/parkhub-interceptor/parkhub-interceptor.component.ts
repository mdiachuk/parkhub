import {Component, Injectable, OnInit} from '@angular/core';
import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from '@angular/common/http';
import {User} from '../interfaces/user';
import {Observable} from 'rxjs';
import {LoginService} from '../service/login.service';



@Injectable()
export class ParkhubInterceptorComponent implements HttpInterceptor {

  constructor(public login: LoginService) { }

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

    request = request.clone({
      setHeaders: {
        'X-AUTH': this.login.getToken() || ''
      }
    });
    return next.handle(request);
  }

}
