import { Component, OnInit } from '@angular/core';
import {Customer, HttpClientService, User, UserRole} from "../service/http-client.service";

@Component({
  selector: 'app-singup',
  templateUrl: './singup.component.html',
  styleUrls: ['./singup.component.scss']
})
export class SingupComponent implements OnInit {

  customer:Customer = new Customer("", true);

  userRole:UserRole = new UserRole("1")

  users:User = new User("","",this.customer,"","",this.userRole);

  constructor(
    private httpClientService:HttpClientService
  ) { }

  ngOnInit() {

  }

  singUpUser(): void {
    this.httpClientService.createUser(this.users)
      .subscribe( data => {
        alert("User SingUp");
      });

  };


  handleSuccessfulResponse(response)
  {
    this.users=response;
  }
}
