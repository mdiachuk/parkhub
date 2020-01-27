import { Component, OnInit } from '@angular/core';
import { User } from '../model/user';
import { FormGroup, FormBuilder } from '@angular/forms';
import { SignUpService } from '../service/signup.service';
import { MatSnackBar } from '@angular/material';
import { HttpClient } from '@angular/common/http';
import { ConfirmPasswordValidator } from '../validation/confirm-password.validator';

@Component({
  selector: 'app-user-signup',
  templateUrl: './user-signup.component.html',
  styleUrls: ['./user-signup.component.scss']
})
export class UserSignupComponent implements OnInit {

  signupForm: FormGroup;
  user: User;
  message: string;
  isCreated: boolean;

  constructor(private signUpService: SignUpService, private fb: FormBuilder,
    private http: HttpClient, private snackBar: MatSnackBar) { }

  ngOnInit() {
    this.signupForm = this.fb.group({
      firstName: [''],
      lastName: [''],
      email: [''],
      phoneNumber: [''],
      password: [''],
      confirmPassword: ['']
    }, { validator: ConfirmPasswordValidator.matchPassword });
    this.isCreated = false;
  }

  register(): void {
    this.user = new User(this.signupForm.get('firstName').value,
      this.signupForm.get('lastName').value,
      this.signupForm.get('email').value,
      this.signupForm.get('phoneNumber').value,
      this.signupForm.get('password').value,
      this.signupForm.get('confirmPassword').value);

    this.signUpService.registerUser(this.user).subscribe(response => {
      this.isCreated = true;
    }, err => {
      this.message = err.error;
      this.openSnackBar(this.message);
    });
  }

  reset(): void {
    this.ngOnInit();
    this.signupForm.markAsPristine();
    this.signupForm.reset();
  }

  openSnackBar(message: string) {
    this.snackBar.open(message, 'Close', {
      duration: 4000,
    });
  }

}
