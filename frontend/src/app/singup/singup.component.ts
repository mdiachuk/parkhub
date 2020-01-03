import {Component, NgModule, OnInit} from '@angular/core';
import {ConfirmPass, Customer, HttpClientService, User, RoleDTO} from "../service/http-client.service";




@Component({
  selector: 'app-singup',
  templateUrl: './singup.component.html',
  styleUrls: ['./singup.component.scss']
})
export class SingupComponent implements OnInit {

  customer:Customer = new Customer("", true);

  roleDTO:RoleDTO = new RoleDTO("USER")

  users:User = new User("","",this.customer,"","",this.roleDTO,"");


  confirmPass:ConfirmPass = new ConfirmPass("");

  constructor(
    private httpClientService:HttpClientService
  ) { }

  ngOnInit() {

  }

  singUpUser(): void {
    if (this.users.password==this.confirmPass.confirmPass){
      this.httpClientService.createUser(this.users)
        .subscribe( data => {
            alert("User SingUp");
            window.location.href='/home';
          },
          err => {
            // alert("User SingUp");
            // window.location.href='/home';
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
