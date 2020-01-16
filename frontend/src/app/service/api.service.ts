import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { PhoneNumber } from '../parkoff/phoneNumber';
import { CheckOutResponse } from '../parkoff/checkOutResponse';




@Injectable({
  providedIn: 'root'
})
export class ApiService {
  private API_URL = '/api';

  constructor(
    private http: HttpClient
  ) { }

  checkPayout(pn: PhoneNumber): Observable<CheckOutResponse> {
    const body = {phoneNumber: pn.phoneNumber};
    return this.http.post<CheckOutResponse>(`${this.API_URL}/cancel`, body);
  }
}