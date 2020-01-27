import {Component, Inject} from '@angular/core';
import {MatDialogRef} from '@angular/material/dialog';
import { ParkingDetail } from './parking-detail';
import { FormControl, Validators} from '@angular/forms';

@Component({
  selector: 'app-parking-detail-dialog',
  templateUrl: 'parking-detail-dialog-component.html',
})
export class AddressDialog {

  parkingDialogDTO: ParkingDetail = new ParkingDetail;
  input = new FormControl('', [Validators.required]);
  

  constructor(
    public dialogRef: MatDialogRef<AddressDialog>) {}

  onNoClick(): void {
    this.dialogRef.close();
  }

  getErrorMessage() {
    if (this.input.hasError('required'))
    return 'You must enter a value';
   }
}