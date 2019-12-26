import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs/BehaviorSubject';

@Injectable()
export class DataService {

  private messageSource = new BehaviorSubject<string>('Default msg');
  private isLogged = new BehaviorSubject<boolean>(true);
  private isAdmin = new BehaviorSubject(false);
  private isManager = new BehaviorSubject(false);

  currentMessage = this.messageSource.asObservable();
  currentIsLogged = this.isLogged.asObservable();
  currentIsAdmin = this.isAdmin.asObservable();
  currentIsManager = this.isManager.asObservable();

  constructor() { }
  changeMessage(message: string) {
    this.messageSource.next(message);
  }
  changeIsLogged(isLogged: boolean) { this.isLogged.next(isLogged); }
  changeIsAdmin(isAdmin: boolean) { this.isAdmin.next(isAdmin); }
  changeIsManager(isManager: boolean) { this.isManager.next(isManager); }
}
