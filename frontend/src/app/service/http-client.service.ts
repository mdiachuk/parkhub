import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ParkingModel } from '../model/parking.model';
import { Router } from '@angular/router';
import { Manager } from '../model/manager';
import { Admin } from '../Classes/admin';
import {UserInfo} from '../interfaces/userInfo';
// import {User} from '../interfaces/user';
import { Login } from '../interfaces/login';
import * as jwt_decode from 'jwt-decode';
import { userRole } from '../model/userRole';
import { AdminTicketDetail } from '../Classes/admin-ticket-detail';

export class User {
  constructor(
    public firstName: string,
    public lastName: string,
    public customer: Customer,
    public email: string,
    public password: string,
    public token: string

  ) {
  }

}
export class RoleDTO {
  constructor(
    public roleDTO: string
  ) {
  }
}



export class UserPassword {
  constructor(
    public id: string,
    public password: string,
    public newPassword: string,

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
    const body = { email: login.email, password: login.password };
    return this.http.post<User>('http://localhost:8080/api/login', body);

    // return this.http.post<User>('/api/login', body);
  }




  oauthlogin(): Observable<User> {
    // const options = {
    //   headers: new HttpHeaders().append('Access-Control-Allow-Origin', '*')
    // }
    // const httpOptions = {
    //   headers: new HttpHeaders({
    //     'Access-Control-Allow-Origin':'*'
    //   })
    // };
    console.log("in service");
    return this.http.get<User>('http://localhost:8080/api/login/google', { withCredentials: true });


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
    return this.httpClient.post<User>("/api/signup/user", user);

  }

}

@Injectable()
export class ParkingService {

  private serviceUrl = 'home';

  // 'http://localhost:4200/assets/parkings.json';

  constructor(
    private http: HttpClient
  ) {
  }

  getparking(): Observable<ParkingModel[]> {

    return this.http.get<ParkingModel[]>(this.serviceUrl);

  }

}

@Injectable({
  providedIn: 'root'
})
export class ParkingServiceService {

  private parkingUrl: string;

  constructor(private http: HttpClient, private router: Router) {
    this.parkingUrl = 'manager/parking';
  }

  public save(parking: ParkingModel): Observable<ParkingModel> {
    return this.http.post<ParkingModel>(this.parkingUrl, parking);
    // this.router.navigate(['login'], { queryParams: { returnUrl: this.parkingUrl }});
  }
}

@Injectable({
  providedIn: 'root'
})
export class ManagerService {


  constructor(private http: HttpClient) {
  }

  registerManager(manager: Manager) {

    return this.http.post('/api/signup/manager', manager);
  }
}

@Injectable({
  providedIn: 'root'
})
export class AdminService {

  constructor(private http: HttpClient) {
  }

  getUserById(id: number): Observable<Admin> {
    return this.http.get<Admin>(`/api/admin/manager/${id}`);
  }

  updateRole(admin: Admin) {
    this.http.post(`/api/admin/manager/${admin.id}`, admin).subscribe(res => console.log('ok'));
  }

  setTicketAsSolved(adminTicketDetail: AdminTicketDetail){
    this.http.post(`/api/admin/ticket/setsolved/${adminTicketDetail.id}`, adminTicketDetail).subscribe(res => console.log('ok'));
  }
}

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private decoded: UserInfo;
  private decodedROLE: userRole;

  constructor(private http: HttpClient) {

  }

  getUserID(): string {
    const token = localStorage.getItem('TOKEN');
    if (token) {
     this.decoded = jwt_decode(token);
     return this.decoded.id.toString();
    } else {
      return '';
    }
  }
  getData() {
    return this.http.get('/api/user/' + this.getUserID());
  }
  PostData(userInfo: UserInfo) {
    return this.http.post('api/user/' + this.getUserID(), userInfo);
  }
  PostDataPassword(userPass: UserPassword) {
    return this.http.post('/api/user/password/' + this.getUserID(), userPass);
  }

  getUserROLE(): string {
    const token = localStorage.getItem('TOKEN');
    if (token) {
     this.decodedROLE = jwt_decode(token);
     return this.decodedROLE.role.toString();
    } else {
      return '';
    }
  }

}

