import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Manager } from '../model/manager';

@Injectable({
  providedIn: 'root'
})
export class ManagerService {

  baseUrl = '/api/manager';

  constructor(private http: HttpClient) { }

  registerManager(manager: Manager) {
    return this.http.post(this.baseUrl + '/signup', manager);
  }
}
