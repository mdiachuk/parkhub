import { Injectable, ErrorHandler }   from '@angular/core';
import { HttpClient }   from '@angular/common/http';
import { Observable, of }   from 'rxjs';
//import { catchError, map, tap } from 'rxjs/operators';


import 'rxjs/add/operator/map';
import { Parking } from '../models/parking.model';
@Injectable()
export class ParkingService1 {

  private serviceUrl =  'api/home';
    //'http://localhost:4200/assets/parkings.json';


  constructor(private http: HttpClient) { }

  getparking(): Observable<Parking[]> {

   return this.http.get<Parking[]>(this.serviceUrl);


  }

}
