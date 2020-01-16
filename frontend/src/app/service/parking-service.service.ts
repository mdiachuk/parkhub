import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { Parking } from '../add-parking/parking';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class ParkingServiceService {

  private parkingUrl: string;

  constructor(private http: HttpClient ) {
    this.parkingUrl = 'api/manager/parking';
  }

  public save(parking: Parking) : Observable<Parking>  {
    console.log("service", parking, this.parkingUrl)
    return this.http.post<Parking>(this.parkingUrl, parking);
    //this.router.navigate(['login'], { queryParams: { returnUrl: this.parkingUrl }});
  }
}
