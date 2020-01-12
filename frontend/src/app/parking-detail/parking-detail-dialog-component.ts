import {Component, Inject} from '@angular/core';
import {MatDialogRef} from '@angular/material/dialog';
import { ParkingDetail } from './parking-detail';

@Component({
  selector: 'app-parking-detail-dialog',
  templateUrl: 'parking-detail-dialog-component.html',
})
export class AddressDialog {

  parkingDialogDTO: ParkingDetail = new ParkingDetail;

  constructor(
    public dialogRef: MatDialogRef<AddressDialog>) {}

  onNoClick(): void {
    this.dialogRef.close();
  }
}