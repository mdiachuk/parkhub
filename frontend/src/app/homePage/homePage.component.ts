import {Component, OnInit} from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { UserService } from '../service/http-client.service';
import { DataService } from '../DataService/data.service';

@Component({
  selector: 'app-page',
  templateUrl: './homePage.component.html',
  styleUrls: ['./homePage.component.scss']
})
export class PageComponent implements OnInit {

  childSearch: string;
  search: string;
  role: string;
  cookieValue: string;


  constructor(private addservice:UserService,public data: DataService,public cookieService: CookieService) {
  }

  ngOnInit() {
    if (this.cookieService.check('TOKEN')) {
    this.cookieValue = this.cookieService.get('TOKEN');
    console.log("cook",this.cookieService.getAll());
    localStorage.setItem('TOKEN', this.cookieValue);
    this.cookieService.deleteAll();
     this.role = this.addservice.getUserROLE();
    // console.log(this.cookieValue)
    if (this.role === 'USER') {
      this.changeIsLogged(true);
      this.changeIsUser(true);
      this.changeIsManager(false);
      this.changeIsAdmin(false);
    } else if (this.role === 'ADMIN') {
      this.changeIsAdmin(true);
      this.changeIsManager(false);
      this.changeIsUser(false);
      this.changeIsLogged(true);
    } else if (this.role === 'MANAGER') {
      this.changeIsManager(true);
      this.changeIsAdmin(false);
      this.changeIsUser(false);
      this.changeIsLogged(true);
    }
  }}
  addSearch() {
    this.childSearch = this.search;
  }

  public changeIsLogged(isLogged: boolean) {
    this.data.changeIsLogged(isLogged);
  }

  public changeIsUser(isUser: boolean) {
    this.data.changeIsUser(isUser);
  }

  public changeIsAdmin(isAdmin: boolean) {
    this.data.changeIsAdmin(isAdmin);
  }

  public changeIsManager(isManager: boolean) {
    this.data.changeIsManager(isManager);
  }


}
