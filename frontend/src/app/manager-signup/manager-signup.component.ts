import { Component, OnInit } from '@angular/core';
import { FormControl, Validators, FormGroup, FormBuilder } from '@angular/forms';
import { ManagerSignupMessage } from '../manager-signup-message';
import { ConfirmPasswordValidator } from '../validation/confirm-password.validator';
import { Manager } from '../model/manager';
import { ManagerService } from '../service/manager.service';
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
  result: ManagerSignupMessage;
  message: string;
  isCreated: boolean;

  constructor(private managerService: ManagerService, private fb: FormBuilder,
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
    this.result = new ManagerSignupMessage();
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
    this.managerService.registerManager(this.manager).subscribe(response => {
      if (response.created === true) {
        this.isCreated = true;
      } else {
        this.message = response.description;
        this.openSnackBar(this.message);
      }
    }, err => {
      if (err.error.status === 500) {
        this.message = 'Congratulations! You have just broken our site :) Please, try again later.';
        this.openSnackBar(this.message);
      } else {
        this.message = err.error.description;
        this.openSnackBar(this.message);
      }
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
