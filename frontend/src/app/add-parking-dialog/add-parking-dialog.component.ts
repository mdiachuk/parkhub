import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';

export interface AddParkingDialogData {
  message: string;
}
@Component({
  selector: 'app-add-parking-dialog',
  templateUrl: './add-parking-dialog.component.html',
  styleUrls: ['./add-parking-dialog.component.scss']
})
export class AddParkingDialogComponent implements OnInit {

  constructor(
    public dialogRef: MatDialogRef<AddParkingDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: AddParkingDialogData
  ) { }

  ngOnInit() {
  }

  onClose(): void {
    this.dialogRef.close();
  }

}


