import {Component, NgModule} from '@angular/core';
import {DataService} from './DataService/data.service';
import {Router} from '@angular/router';
import {HttpClient} from '@angular/common/http';

export class AppModule { }
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'frontend';


  constructor(private router: Router,
              private data: DataService,
              public http: HttpClient) {
  }

  ping(): void {
    this.http.get('/api/*')
      .subscribe(
        data => console.log(data),
        err => console.log(err)
      );
  }

  logout(): void {
    this.changeIsAdmin(false);
    this.changeIsManager(false);
    this.changeIsLogged(false);
    localStorage.removeItem('TOKEN');
    this.router.navigate(['/home']);
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

