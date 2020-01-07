import { Component, OnInit } from '@angular/core';
import { ApiService } from '../api.service';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { AlertDialogComponent } from '../alert-dialog/alert-dialog.component';
import {PhoneNumber} from '../phoneNumber';
import {TranslateService} from '@ngx-translate/core';

@Component({
  selector: 'app-parkoff',
  templateUrl: './parkoff.component.html',
  styleUrls: ['./parkoff.component.scss']
})
export class ParkoffComponent implements OnInit {
  pn: PhoneNumber = new PhoneNumber();
  code: number;
  constructor(
    private translate: TranslateService,
    private api: ApiService,
    public dialog: MatDialog
  ) { }

  openDialog(title: string, message: string): MatDialogRef<AlertDialogComponent> {
    return this.dialog.open(AlertDialogComponent, {
      width: '350px',
      data: { title, message }
    });
  }

  ngOnInit() {
  }

  submit(): void {
    if (!this.pn || !this.pn.phoneNumber.length || this.pn.phoneNumber.length < 12) {
      this.openDialog(this.translate.instant('Error'), this.translate.instant('WrongPhoneNumberSpecified'));
      return;
    }

    this.api.checkPayout(this.pn).subscribe(result => {
      const title = result.status ? this.translate.instant(
        'Success') : this.translate.instant('Error');
      const message = result.status ? this.translate.instant('The price of your booking is').concat(result.price.toString()).concat('UAH') : this.checkStatusCode(this.code);

      this.openDialog(title, message).afterClosed().subscribe(() => {
        if (result.status) {
          window.location.href = '/home';
        } else {
          window.location.reload();
        }
      });
    });
  }

  checkStatusCode(code: number): string {
    if (code === 16) {
      return 'Customer with this phone number was not found, please, check the correct input';
    }
    if (code === 32) {
      return 'No pre-order for this phone number found';
    }
    if (code === 64) {
      return 'Your reservation is already in progress';
    }
  }
}
