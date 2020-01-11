import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { Router } from '@angular/router';
import { PhoneNumber } from '../phoneNumber';


@Injectable({
  providedIn: 'root'
})
export class Oauth2googleService {
  private customerUrl: string;

  constructor(private http: HttpClient ) {
    this.customerUrl = 'api/customer';
   }

   public save(parking: PhoneNumber) : Observable<PhoneNumber>  {
    console.log("service", parking, this.customerUrl)
   return this.http.put<PhoneNumber>(this.customerUrl, parking);
   //this.router.navigate(['login'], { queryParams: { returnUrl: this.parkingUrl }});
 }

//  public oAuthJwt() : Observable<string>  {
//  return this.http.get<string>("http://localhost:8080/api/oauthJwtToken");
//  //this.router.navigate(['login'], { queryParams: { returnUrl: this.parkingUrl }});
// }
}
