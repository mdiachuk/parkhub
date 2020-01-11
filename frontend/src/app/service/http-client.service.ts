import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from 'rxjs';
import {Parking} from '../models/parking.model';
import {Router} from "@angular/router";
import {Manager} from '../models/manager';
import {Admin} from '../Classes/admin';
import {UserInfo} from '../interfaces/userInfo';
import { Login } from '../interfaces/login';

export class User {
  constructor(
    public firstName: string,
    public lastName: string,
    public customerDTO: Customer,
    public email: string,
    public password: string,
    public roleDTO: RoleDTO,
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
    const body = {email: login.email, password: login.password};
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
    return this.httpClient.post<User>("api/signup/user", user);
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
    return this.http.get<Admin>(`/api/admin/${id}`);
  }

  updateRole(admin: Admin) {
    this.http.post("/api/admin/{id}", admin).subscribe(res => console.log("ok"));
  }
}

@Injectable({
  providedIn: 'root'
})
export class UserService {


  constructor(private http: HttpClient) {


  }

  getUserID(){
    // localStorage.setItem('TOKEN', '{id}: 1 , email : lolkek');
    var idUser = '';
    var test= '';
    var i = localStorage.getItem('TOKEN').indexOf('id');
    while(test != ','){
      if(localStorage.getItem('TOKEN')[i] === ','){
        break;
      }else{
        test = localStorage.getItem('TOKEN')[i];
        if(( parseInt(localStorage.getItem('TOKEN')[i])) >= 0 ){
          idUser = idUser + localStorage.getItem('TOKEN')[i];
        }
        i++;
      }
    }


    return idUser;
  }
  getData(){
    return this.http.get('/api/user/' + this.getUserID());
  }
  PostData(userInfo : UserInfo){
    return this.http.post('api/user', userInfo);
  }
  PostDataPassword(userPass : UserPassword){
    return this.http.post('/api/user/password', userPass);
  }
}
