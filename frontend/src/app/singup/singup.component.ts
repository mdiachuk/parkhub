import { Component, OnInit } from '@angular/core';
import {HttpClientService, User} from "../service/http-client.service";

@Component({
  selector: 'app-singup',
  templateUrl: './singup.component.html',
  styleUrls: ['./singup.component.scss']
})
export class SingupComponent implements OnInit {

  users:User = new User("","","",
    "","","","","");

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
