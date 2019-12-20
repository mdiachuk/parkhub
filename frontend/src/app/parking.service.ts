import { Injectable } from '@angular/core';
import { ParkingItem } from './parking-item';
import { Observable, of } from 'rxjs';
import {HttpClient} from "@angular/common/http"

@Injectable({
  providedIn: 'root'
})
export class ParkingService {

  private usersUrl: string;
 
  constructor(private http: HttpClient) {
    this.usersUrl = '/api/parkings';
  }

  getData(): Observable<ParkingItem[]> {
    // return of (EXAMPLE_DATA);
    return this.http.get<ParkingItem[]>(this.usersUrl);
  }
}
