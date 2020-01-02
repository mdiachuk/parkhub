import { Component, OnInit } from '@angular/core';
import { ResetPasswordService } from '../service/reset-password.service';
import { Router, ActivatedRoute } from '@angular/router';
import { Token } from '../model/token';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-verify-email',
  templateUrl: './verify-email.component.html',
  styleUrls: ['./verify-email.component.scss']
})
export class VerifyEmailComponent implements OnInit {

  token: string;
  tokenDTO: Token;
  isExpired: boolean;
  loading: boolean;
  message: string;
  isError: boolean;
  view: number;

  constructor(private resetPasswordService: ResetPasswordService, private router: Router,
    private activatedRoute: ActivatedRoute, private snackBar: MatSnackBar) { }

  ngOnInit() {
    this.checkIsExpired();
  }

  checkIsExpired() {
    this.activatedRoute.queryParams.subscribe(params => {
      this.token = params.token;
    });
    this.resetPasswordService.checkIsExpired(this.token).subscribe(response => {
      this.verify();
    }, err => {
      if (err.status === 500) {
        this.isError = true;
      }
      this.view = 1;
      this.message = err.error;
    });
  }

  verify() {
    this.resetPasswordService.verifyEmail(this.token).subscribe(response => {
      this.router.navigate(['login']).then((navigated: boolean) => {
        if (navigated) {
          this.openSnackBar('Your email address was successfully verifyed!');
        }
      });
    });
  }

  resendToken() {
    this.loading = true;
    this.tokenDTO = new Token(this.token, 'EMAIL');
    this.resetPasswordService.resendTokenToEmail(this.tokenDTO).subscribe(response => {
      this.loading = false;
      this.view = 2;
    }, err => {
      this.message = err.error;
      if (err.status === 500) {
        this.isError = true;
        this.view = 1;
      } else {
        this.openSnackBar(this.message);
      }
      this.loading = false;
    });
  }

  openSnackBar(message: string) {
    this.snackBar.open(message, 'Close', {
      duration: 4000,
    });
  }
}
