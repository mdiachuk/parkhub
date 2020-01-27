import { Component, OnInit } from '@angular/core';
import { FormControl, Validators, FormGroup, FormBuilder } from '@angular/forms';
import { ConfirmPasswordValidator } from "../validation/confirm-password.validator";
import { Manager } from '../model/manager';
import { SignUpService } from "../service/signup.service";
import { HttpClient } from '@angular/common/http';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-manager-signup',
  templateUrl: './manager-signup.component.html',
  styleUrls: ['./manager-signup.component.scss']
})
export class ManagerSignupComponent implements OnInit {

  signupForm: FormGroup;
  manager: Manager;
  message: string;
  isCreated: boolean;

  constructor(private signUpService: SignUpService, private fb: FormBuilder,
    private http: HttpClient, private snackBar: MatSnackBar) { }

  ngOnInit() {
    this.signupForm = this.fb.group({
      firstName: [''],
      lastName: [''],
      companyName: [''],
      usreouCode: [''],
      email: [''],
      phoneNumber: [''],
      password: [''],
      confirmPassword: [''],
      comment: [''],
      checkbox: false
    }, { validator: ConfirmPasswordValidator.matchPassword });
    this.isCreated = false;
  }

  register(): void {
    this.manager = new Manager(this.signupForm.get('firstName').value,
      this.signupForm.get('lastName').value,
      this.signupForm.get('companyName').value,
      this.signupForm.get('usreouCode').value,
      this.signupForm.get('email').value,
      this.signupForm.get('phoneNumber').value,
      this.signupForm.get('password').value,
      this.signupForm.get('confirmPassword').value,
      this.signupForm.get('comment').value);
    this.signUpService.registerManager(this.manager).subscribe(response => {
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
