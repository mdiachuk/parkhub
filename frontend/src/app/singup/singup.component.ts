import {Component, NgModule, OnInit} from '@angular/core';
import {ConfirmPass, Customer, HttpClientService, User, RoleDTO} from "../service/http-client.service";
import {MatSnackBar} from '@angular/material';




@Component({
  selector: 'app-singup',
  templateUrl: './singup.component.html',
  styleUrls: ['./singup.component.scss']
})


export class SingupComponent implements OnInit {

  customer:Customer = new Customer("", true);

  users:User = new User("","",this.customer,"","","");

  confirmPass:ConfirmPass = new ConfirmPass("");

  constructor(
    private httpClientService:HttpClientService, private snackBar: MatSnackBar
  ) { }

  ngOnInit() {

  }

  singUpUser(): void {

    if (this.users.password==this.confirmPass.confirmPass){
      this.httpClientService.createUser(this.users)
        .subscribe( data => {
           this.openSnackBar('User SingUp');
            window.location.href='/home';
          },
          err => {
            this.openSnackBar("Email or Telephone are use");
          });
    } else {
      this.openSnackBar("Pass not Confirm");
    }


  };

  openSnackBar(message: string) {
    this.snackBar.open(message, 'Close', {
      duration: 4000,
    });
  }


  handleSuccessfulResponse(response)
  {
    this.users=response;
  }
}
