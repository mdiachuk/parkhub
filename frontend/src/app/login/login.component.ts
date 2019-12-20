import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {LoginService} from '../services/login.service';
import {FormBuilder} from '@angular/forms';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  constructor(private router: Router, private fb: FormBuilder, private loginSvc: LoginService) {
  }

  loginForm = this.fb.group({
    username: [''],
    password: ['']
  })


  ngOnInit() {
  }

  login(): void {
    this.loginSvc.login({email: this.loginForm.get('username').value, password: this.loginForm.get('password').value}).subscribe(
      user => {
        console.log(user);
        localStorage.setItem('TOKEN', user.token);
        this.router.navigate(['/home']);
      }
    );
  }


}

