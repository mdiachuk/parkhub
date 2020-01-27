import { Component, OnInit } from '@angular/core';
import { User } from '../model/user';
import { FormGroup, FormBuilder } from '@angular/forms';
import { SignUpService } from '../service/signup.service';
import { HttpClient } from '@angular/common/http';
import { ConfirmPasswordValidator } from '../validation/confirm-password.validator';
import { AddParkingDialogComponent } from '../add-parking-dialog/add-parking-dialog.component';
import { MatDialogRef, MatDialog } from '@angular/material';

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
  loading: boolean;

  constructor(private signUpService: SignUpService, private fb: FormBuilder,
    private http: HttpClient, private dialog: MatDialog) { }

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
    this.loading = false;
  }

  register(): void {
    this.loading = true;
    this.user = new User(this.signupForm.get('firstName').value,
      this.signupForm.get('lastName').value,
      this.signupForm.get('email').value,
      this.signupForm.get('phoneNumber').value,
      this.signupForm.get('password').value,
      this.signupForm.get('confirmPassword').value);
    this.signUpService.registerUser(this.user).subscribe(response => {
      this.isCreated = true;
      this.loading = false;
    }, err => {
      this.message = err.error;
      this.openDialog(this.message);
      this.loading = false;
    });
  }

  reset(): void {
    this.ngOnInit();
    this.signupForm.markAsPristine();
    this.signupForm.reset();
  }

  openDialog( message: string): MatDialogRef<AddParkingDialogComponent> {
    return this.dialog.open(AddParkingDialogComponent, {
      width: '350px',
      data: {message}
    });
  }
}
