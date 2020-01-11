import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';

import {FormBuilder} from '@angular/forms';
import {DataService} from '../DataService/data.service';
import {LoginService} from '../services/login.service';
import {MatSnackBar} from '@angular/material';
import {TranslateService} from '@ngx-translate/core';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  constructor(private router: Router,
              private fb: FormBuilder,
              private loginSvc: LoginService,
              public data: DataService,
              private snackBar: MatSnackBar,
              private translate: TranslateService) {
  }

  loginForm = this.fb.group({
    username: [''],
    password: ['']
  });
  code: number;

  ngOnInit() {
  }

  login(): void {
    this.loginSvc.login({email: this.loginForm.get('username').value,
                              password: this.loginForm.get('password').value}).subscribe(user => {
        if (user) {
          if (user.role === 'USER') {
            this.changeIsLogged(true);
            this.changeIsUser(true);
            this.changeIsManager(false);
            this.changeIsAdmin(false);
            console.log(user);
          } else
          if (user.role === 'ADMIN') {
            this.changeIsAdmin(true);
            this.changeIsManager(false);
            this.changeIsUser(false);
            this.changeIsLogged(true);
          } else
          if (user.role === 'MANAGER') {
            this.changeIsManager(true);
            this.changeIsAdmin(false);
            this.changeIsUser(false);
            this.changeIsLogged(true);
          }
          localStorage.setItem('TOKEN', user.token);
          this.router.navigate(['/home']);
        } else {
          this.changeIsLogged(false);
          alert('Sorry, you are\'t logged. Try again please!');
        }
      }, err => {
        this.code = err.error;
        this.openSnackBar(this.checkStatusCode(this.code));
      }
    );
  }

  checkStatusCode(code: number): string {
    if (code === 1) {
      return this.translate.instant('Your account was blocked for 24 hours because of 3 unsuccessful tries to login. Please, try again later.');
    }
    if (code === 2) {
      return this.translate.instant('Cannot activate account: less than 24 hours have passed.');
    }
    if (code === 4) {
      return this.translate.instant('No account with such email was found.');
    }
    if (code === 8) {
      return this.translate.instant('Please enter valid credentials!');
    }if (code === 256) {
      return 'Your account is not verified.';
    }
  }

  openSnackBar(message: string) {
    this.snackBar.open(message, 'Close', {
      duration: 4000,
    });
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

  public changeIsUser(isUser: boolean) {
    this.data.changeIsUser(isUser);
  }

  onClickMe():void{
    console.log("click");
    this.loginSvc.oauthlogin().subscribe(
      user => {console.log(user);
      this.router.navigate(['/home'])}
    );
  }



}

