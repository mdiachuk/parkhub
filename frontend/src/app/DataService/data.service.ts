import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs/BehaviorSubject';
import * as jwt_decode from "jwt-decode";

@Injectable()
export class DataService {

  private messageSource = new BehaviorSubject<string>('Default msg');
  private isLogged = new BehaviorSubject<boolean>(false);
  private isAdmin = new BehaviorSubject(false);
  private isManager = new BehaviorSubject(false);
  private isUser = new BehaviorSubject(false);

  currentMessage = this.messageSource.asObservable();
  currentIsLogged = this.isLogged.asObservable();
  currentIsAdmin = this.isAdmin.asObservable();
  currentIsManager = this.isManager.asObservable();
  currentIsUser = this.isUser.asObservable();

  constructor() {
    const token = localStorage.getItem('TOKEN');
    if (token) {
      this.changeIsLogged(true);
      const user = jwt_decode(token);
      if (user) {
        if (user.role === 'USER') {
          this.changeIsUser(true);
          this.changeIsManager(false);
          this.changeIsAdmin(false);
        } else
          if (user.role === 'ADMIN') {
            this.changeIsAdmin(true);
            this.changeIsManager(false);
            this.changeIsUser(false);
          } else
            if (user.role === 'MANAGER') {
              this.changeIsManager(true);
              this.changeIsAdmin(false);
              this.changeIsUser(false);
            }
      } else {
        this.changeIsLogged(false);
        this.changeIsUser(false);
        this.changeIsManager(false);
        this.changeIsAdmin(false);
      }
    }
  }
  changeMessage(message: string) {
    this.messageSource.next(message);
  }
  changeIsLogged(isLogged: boolean) { this.isLogged.next(isLogged); }
  changeIsAdmin(isAdmin: boolean) { this.isAdmin.next(isAdmin); }
  changeIsManager(isManager: boolean) { this.isManager.next(isManager); }
  changeIsUser(isUser: boolean) { this.isUser.next(isUser); }
}
