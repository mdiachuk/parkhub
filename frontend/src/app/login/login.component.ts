import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';

import {FormBuilder} from '@angular/forms';
import {DataService} from '../DataService/data.service';
import {LoginService} from '../services/login.service';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  constructor(private router: Router,
              private fb: FormBuilder,
              private loginSvc: LoginService,
              public data: DataService) {
  }

  loginForm = this.fb.group({
    username: [''],
    password: ['']
  });

  ngOnInit() {
  }

  login(): void {
    this.loginSvc.login({email: this.loginForm.get('username').value,
                              password: this.loginForm.get('password').value}).subscribe(user => {
        if (user) {
          this.changeIsLogged(true);
          console.log(user);
          if (user.role === 'ADMIN') {
            this.changeIsAdmin(true);
            this.changeIsManager(false);
          } else if (user.role === 'MANAGER') {
            this.changeIsManager(true);
            this.changeIsAdmin(false);
          }
          localStorage.setItem('TOKEN', user.token);
          this.router.navigate(['/home']);
        } else {
          this.changeIsLogged(false);
          alert('Sorry, you are\'t logged. Try again please!');
        }
      }, err => {
        console.log(err.error);
        alert(err.error);
      }
    );
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

  public  openModal(text: string) {
    this.data.changeMessage(text);
    // this.modalService.show(CongratulationComponent);
  }


}

