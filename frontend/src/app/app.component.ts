import {Component, NgModule} from '@angular/core';
import {TranslateService} from '@ngx-translate/core';

import {DataService} from './DataService/data.service';
import {Router} from '@angular/router';
import {TranslateArrayService} from './service/translatearray.service';
import {tap} from 'rxjs/operators';
import {HttpClient} from '@angular/common/http';

export class AppModule { }
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'frontend';

  constructor(public translate: TranslateService,
              private router: Router,
              private data: DataService,
              private translateArrayService: TranslateArrayService,
              public http: HttpClient) {
    translate.setDefaultLang('en');
    translate.addLangs(['en', 'ua']);
    const browserLang = translate.getBrowserLang();
    translate.use(browserLang.match(/en|ua/) ? browserLang : 'en');
    this.translateArrayService.changeArray();
  }

  useLanguage(language: string) {
    this.translate.use(language).subscribe(r => this.translateArrayService.changeArray());
  }

  logout(): void {
    this.changeIsAdmin(false);
    this.changeIsManager(false);
    this.changeIsLogged(false);
    this.changeIsUser(false);
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

  ping(): void {
    this.http.get('/api/*')
      .subscribe(
        data => console.log(data),
        err => console.log(err)
      );
  }

  public changeIsUser(isUser: boolean) {
    this.data.changeIsUser(isUser);
  }
}

