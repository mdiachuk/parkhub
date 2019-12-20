import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Manager } from '../model/manager';
import { ManagerSignupMessage } from '../manager-signup-message';

@Injectable({
  providedIn: 'root'
})
export class ManagerService {

  baseUrl = '/api/manager/signup';

  constructor(private http: HttpClient) { }

  registerManager(manager: Manager) {
    return this.http.post<ManagerSignupMessage>(this.baseUrl, manager);
  }
}
