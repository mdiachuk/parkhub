import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse, HttpHeaders } from '@angular/common/http';
import { Login } from '../interfaces/login';
import { Observable } from 'rxjs';
import {User} from '../interfaces/user';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private http: HttpClient) { }

  public getToken(): string {
    return localStorage.getItem('TOKEN');
  }


  login(login: Login): Observable<User> {
    const body = {email: login.email, password: login.password};
    return this.http.post<User>('/api/login', body);
  }






  oauthlogin(): Observable<User> {
    // const options = {
    //   headers: new HttpHeaders().append('Access-Control-Allow-Origin', '*')
    // }
    // const httpOptions = {
    //   headers: new HttpHeaders({
    //     'Access-Control-Allow-Origin':'*'
    //   })
    // };
    console.log("in service");
    return this.http.get<any>('http://localhost:8080/api/login/google', { withCredentials: true });


  }

}
