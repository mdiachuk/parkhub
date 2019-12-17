import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";

export class User {
  constructor(
  public id:string,
  public icustomerId:string,
  public name:string,
  public secondname:string,
  public telephone:string,
  public email:string,
  public password:string,
  public role:string,
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
    console.log("test call! SingUp User");
    return this.httpClient.post<User>("http://localhost:8081/api/singup", user);
  }

}
