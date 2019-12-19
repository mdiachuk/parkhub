import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";

export class User {
  constructor(
  public name:string,
  public secondname:string,
  public customer:Customer,
  public email:string,
  public pass:string,
  public role: UserRole
  ){}

}

export class UserRole {
  constructor(
    public id:string
  ){}
}

export class Customer {
  constructor(
    public phoneNumber:string,
    public isActive:boolean
  ){}
}

@Injectable({
  providedIn: 'root'
})

export class HttpClientService {

  constructor(
    private httpClient:HttpClient
  ) { }

  getUser(){
    console.log("test call!");
    return this.httpClient.get<User[]>('http://localhost:8081/api/singup')
  }

  public createUser(user) {
    console.log("SingUp User");
    return this.httpClient.post<User>("http://localhost:8081/api/singup", user);
  }

}
