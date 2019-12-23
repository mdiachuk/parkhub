import { Component, OnInit } from '@angular/core';
import { ApiService } from '../api.service';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { AlertDialogComponent } from '../alert-dialog/alert-dialog.component';
import {PhoneNumber} from '../phoneNumber';

@Component({
  selector: 'app-parkoff',
  templateUrl: './parkoff.component.html',
  styleUrls: ['./parkoff.component.scss']
})
export class ParkoffComponent implements OnInit {
  pn: PhoneNumber = new PhoneNumber();
  constructor(
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
    if (!this.pn.phoneNumber || !this.pn.phoneNumber.length || this.pn.phoneNumber.length < 11) {
      this.openDialog('Error', 'Wrong phone number specified!');
      return;
    }

    this.api.checkPayout(this.pn).subscribe(({ status, price }) => {
      const title = status ? 'Success!' : 'Error!';
      const message = status ? `The price of your booking is ${price} $` : `Wrong phone number, please try again`;

      this.openDialog(title, message).afterClosed().subscribe(() => {
        if (status) {
          window.location.href = '/api/home';
        } else {
          window.location.reload();
        }
      });
    });
  }
}
