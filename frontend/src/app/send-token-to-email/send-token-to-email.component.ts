import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ResetPasswordService } from '../service/reset-password.service';
import { Email } from '../model/email';

@Component({
  selector: 'app-send-token-to-email',
  templateUrl: './send-token-to-email.component.html',
  styleUrls: ['./send-token-to-email.component.scss']
})
export class SendTokenToEmailComponent implements OnInit {

  sendTokenToEmailForm: FormGroup;
  email: Email;
  message: string;
  isSent: boolean;

  constructor(private resetPasswordService: ResetPasswordService,
    private fb: FormBuilder, private snackBar: MatSnackBar) { }

  ngOnInit() {
    this.sendTokenToEmailForm = this.fb.group({
      email: ['']
    });
    this.isSent = false;
  }

  sendEmail() {
    this.email = new Email(this.sendTokenToEmailForm.get('email').value);
    this.resetPasswordService.sendTokenToEmail(this.email).subscribe(response => {
      this.isSent = true;
    }, err => {
      this.message = err.error;
      this.openSnackBar(this.message);
    });
  }

  openSnackBar(message: string) {
    this.snackBar.open(message, 'Close', {
      duration: 4000,
    });
  }
}
