import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from 'rxjs';
import {Parking} from '../models/parking.model';
import {Router} from "@angular/router";
import {Manager} from '../models/manager';
import {Admin} from '../Classes/admin';
// import {User} from '../interfaces/user';
import { Login } from '../interfaces/login';

export class User {
  constructor(
    public name: string,
    public secondname: string,
    public customer: Customer,
    public email: string,
    public pass: string,
    public role: UserRole,
    public token: string
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
export class LoginService {

  constructor(private http: HttpClient) { }


  login(login: Login): Observable<User> {
    const body = {email: login.email, password: login.password};
    return this.http.post<User>('/api/login', body);
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

  private serviceUrl = 'home';

  //'http://localhost:4200/assets/parkings.json';

  constructor(
    private http: HttpClient
  ) {
  }

  getparking(): Observable<Parking[]> {

    return this.http.get<Parking[]>(this.serviceUrl);

  }

}

@Injectable({
  providedIn: 'root'
})
export class ParkingServiceService {

  private parkingUrl: string;

  constructor(private http: HttpClient, private router: Router) {
    this.parkingUrl = 'cabinet/addParking';
  }

  public save(parking: Parking): Observable<Parking> {
    return this.http.post<Parking>(this.parkingUrl, parking);
    //this.router.navigate(['login'], { queryParams: { returnUrl: this.parkingUrl }});
  }
}

@Injectable({
  providedIn: 'root'
})
export class ManagerService {


  constructor(private http: HttpClient) {
  }

  registerManager(manager: Manager) {

    return this.http.post('/signup/manager', manager);
  }
}

@Injectable({
  providedIn: 'root'
})
export class AdminService {

  constructor(private http: HttpClient) {
  }

  getUserById(id: number): Observable<Admin> {
    return this.http.get<Admin>(`/api/admin/${id}`);
  }

  updateRole(admin: Admin) {
    this.http.post("/api/admin/{id}", admin).subscribe(res => console.log("ok"));
  }
}