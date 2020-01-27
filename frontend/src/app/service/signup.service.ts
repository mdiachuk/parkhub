import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { User } from '../model/user';
import { Manager } from '../model/manager';

@Injectable({
  providedIn: 'root'
})
export class SignUpService {

  baseUrl = '/api/signup';

  constructor(private http: HttpClient) { }

  registerUser(user: User) {
    return this.http.post(this.baseUrl + '/user', user);
  }

  registerManager(manager: Manager) {
    return this.http.post(this.baseUrl + '/manager', manager);
  }
}
