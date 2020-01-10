import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import {PhoneNumber} from './phoneNumber';

@Injectable({
  providedIn: 'root'
})
export class ApiService {
  private API_URL = '/api';

  constructor(
    private http: HttpClient
  ) { }

  checkPayout(pn: PhoneNumber): Observable<any> {
    const body = {phoneNumber: pn.phoneNumber};
    return this.http.post(`${this.API_URL}/parkoff`, body);
  }
}
