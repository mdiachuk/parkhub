import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';

import {FormBuilder} from '@angular/forms';
import {DataService} from '../DataService/data.service';
import {LoginService} from '../services/login.service';
import {MatSnackBar} from '@angular/material';


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
              private snackBar: MatSnackBar) {
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
      return 'Your account was blocked for 24 hours because of 3 unsuccessful tries to login. Please, try again later.';
    }
    if (code === 2) {
      return 'Your account was blocked. Cannot activate account: less than 24 hours have passed.';
    }
    if (code === 4) {
      return 'No account with such email was found.';
    }
    if (code === 8) {
      return 'Please enter valid credentials!';
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

  public  openModal(text: string) {
    this.data.changeMessage(text);
    // this.modalService.show(CongratulationComponent);
  }

  onClickMe():void{
    console.log("click");
    this.loginSvc.oauthlogin().subscribe(
      user => {console.log(user);
      this.router.navigate(['/home'])}
    );
  }



}

