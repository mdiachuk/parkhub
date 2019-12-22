import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable, of} from 'rxjs';
import {Parking} from '../models/parking.model';

export class User {
  constructor(
    public name: string,
    public secondname: string,
    public customer: Customer,
    public email: string,
    public pass: string,
    public role: UserRole
  ) {
  }

}

export class UserRole {
  constructor(
    public id: string
  ) {
  }
}

export class Customer {
  constructor(
    public phoneNumber: string,
    public isActive: boolean
  ) {
  }
}

export class ConfirmPass {
  constructor(
    public confirmPass: string
  ) {
  }
}

@Injectable({
  providedIn: 'root'
})
export class HttpClientService {

  constructor(
    private httpClient: HttpClient
  ) {
  }

  public createUser(user) {
    console.log("SingUp User");
    return this.httpClient.post<User>("http://localhost:8081/api/v1/singup", user);
  }

}

@Injectable()
export class ParkingService {

  private serviceUrl = '/api/home';

  //'http://localhost:4200/assets/parkings.json';


  constructor(
    private http: HttpClient
  ) {
  }

  getparking(): Observable<Parking[]> {

    return this.http.get<Parking[]>(this.serviceUrl);

  }

}
