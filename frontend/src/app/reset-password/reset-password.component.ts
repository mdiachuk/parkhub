import { Component, OnInit } from '@angular/core';
import { Password } from '../model/password';
import { FormGroup, FormBuilder } from '@angular/forms';
import { ResetPasswordService } from '../service/reset-password.service';
import { ConfirmPasswordValidator } from '../validation/confirm-password.validator';
import { ActivatedRoute, Router } from '@angular/router';
import { Token } from '../model/token';
import { AddParkingDialogComponent } from '../add-parking-dialog/add-parking-dialog.component';
import { MatDialogRef, MatDialog } from '@angular/material';


@Component({
  selector: 'app-reset-password',
  templateUrl: './reset-password.component.html',
  styleUrls: ['./reset-password.component.scss']
})
export class ResetPasswordComponent implements OnInit {

  resetPasswordForm: FormGroup;
  password: Password;
  message: string;
  token: string;
  tokenDTO: Token;
  loading: boolean;
  view: number;
  isError: boolean;

  constructor(private resetPasswordService: ResetPasswordService,
    private fb: FormBuilder, private dialog: MatDialog, private router: Router,
    private activatedRoute: ActivatedRoute) { }

  ngOnInit() {
    this.loading = false;
    this.resetPasswordForm = this.fb.group({
      password: [''],
      confirmPassword: ['']
    }, { validator: ConfirmPasswordValidator.matchPassword });
    this.checkIsExpired();
  }

  checkIsExpired() {
    this.activatedRoute.queryParams.subscribe(params => {
      this.token = params.token;
    });
    this.resetPasswordService.checkIsExpired(this.token).subscribe(response => {
      this.view = 1;
    }, err => {
      if (err.status === 500) {
        this.isError = true;
      }
      this.view = 2;
      this.message = err.error;
    });
  }

  resetPassword() {
    this.loading = true;
    this.password = new Password(this.token, this.resetPasswordForm.get('password').value);
    this.resetPasswordService.resetPassword(this.password).subscribe(response => {
      this.router.navigate(['login']).then((navigated: boolean) => {
        if (navigated) {
          this.loading = false;
          this.openDialog('Your password was successfully reset! Try to login using your new credentials');
        }
      });
    }, err => {
      this.loading = false;
      this.message = err.error;
      this.openDialog(this.message);
    });
  }

  resendToken() {
    this.loading = true;
    this.tokenDTO = new Token(this.token, 'PASSWORD');
    this.resetPasswordService.resendTokenToEmail(this.tokenDTO).subscribe(response => {
      this.loading = false;
      this.view = 3;
    }, err => {
      this.message = err.error;
      if (err.status === 500) {
        this.view = 2;
      } else {
        this.openDialog(this.message);
      }
      this.loading = false;
    });
  }

  openDialog( message: string): MatDialogRef<AddParkingDialogComponent> {
    return this.dialog.open(AddParkingDialogComponent, {
      width: '350px',
      data: {message}
    });
  }
}
