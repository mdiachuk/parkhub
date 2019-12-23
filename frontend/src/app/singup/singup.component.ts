import {Component, NgModule, OnInit} from '@angular/core';
import {ConfirmPass, Customer, HttpClientService, User, UserRole} from "../service/http-client.service";




@Component({
  selector: 'app-singup',
  templateUrl: './singup.component.html',
  styleUrls: ['./singup.component.scss']
})
export class SingupComponent implements OnInit {

  customer:Customer = new Customer("", true);

  userRole:UserRole = new UserRole("1")

  users:User = new User("","",this.customer,"","",this.userRole);

  confirmPass:ConfirmPass = new ConfirmPass("");

  constructor(
    private httpClientService:HttpClientService
  ) { }

  ngOnInit() {

  }

  singUpUser(): void {
    if (this.users.pass==this.confirmPass.confirmPass){
      this.httpClientService.createUser(this.users)
        .subscribe( data => {
            alert("User SingUp");
            window.location.href='/';
          },
          err => {
            alert("Email or Telephone are use");
          });
    } else {
      alert("Pass not Confirm")
    }


  };




handleSuccessfulResponse(response)
  {
    this.users=response;
  }
}
