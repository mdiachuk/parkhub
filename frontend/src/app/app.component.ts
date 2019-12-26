import {Component, NgModule} from '@angular/core';
import {DataService} from './DataService/data.service';

export class AppModule { }
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'frontend';


  constructor(private data: DataService) {
  }

  logout(): void {
    this.changeIsAdmin(false);
    this.changeIsManager(false);
    this.changeIsLogged(false);
    localStorage.removeItem('TOKEN');
  }

  public changeIsLogged(isLogged: boolean) {
    this.data.changeIsLogged(isLogged);
  }

  public changeIsAdmin(isAdmin: boolean) {
    this.data.changeIsAdmin(isAdmin);
  }

  public changeIsManager(isManager: boolean) {
    this.data.changeIsManager(isManager);
  }
}

