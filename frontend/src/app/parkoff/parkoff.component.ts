import { Component, OnInit } from '@angular/core';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { AlertDialogComponent } from './alert-dialog/alert-dialog.component';
import { PhoneNumber } from './phoneNumber';
import {TranslateService} from '@ngx-translate/core';
import { ApiService } from '../service/api.service';

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
      const title =  this.translate.instant('Success');
      const message = this.translate.instant('The price of your booking is').concat(result.price.toString()).concat(this.translate.instant(' UAH'));
      this.openDialog(title, message).afterClosed().subscribe(() => {
        window.location.href = '/home';
      });
    }, error => {
      this.code = error.error;
      const title =  this.translate.instant('Error');
      const message = this.checkStatusCode(this.code);
      this.openDialog(title, message).afterClosed().subscribe(() => {
        window.location.reload();
      });
    });
  }

  checkStatusCode(code: number): string {
    if (code === 32) {
      return this.translate.instant('Customer with this phone number was not found');
    }
    if (code === 64) {
      return this.translate.instant('No pre-order');
    }
  }
}
