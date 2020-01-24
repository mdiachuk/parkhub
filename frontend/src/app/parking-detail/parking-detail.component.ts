import { Component, OnInit } from '@angular/core';
import { ParkingService} from "../parking.service";
import { ParkingDetail} from './parking-detail';
import { ActivatedRoute } from '@angular/router';
import { MatSnackBar} from '@angular/material/snack-bar';
import { MatDialog} from '@angular/material/dialog';
import { AddressDialog } from './parking-detail-dialog-component';
import { FormControl, Validators} from '@angular/forms';
import { flatMap } from 'rxjs/operators';





@Component({
  selector: 'app-parking-detail',
  templateUrl: './parking-detail.component.html',
  styleUrls: ['./parking-detail.component.scss']
})
export class ParkingDetailComponent implements OnInit {

  parkingID: string;
  parkingDTO: ParkingDetail;
  parkingDetail: ParkingDetail;
  buttonStatusList: Array<boolean>;
  input = new FormControl('', [Validators.required]);
  code: number = 0;
  inputValidationStatus: boolean = false;

  constructor(private parkingService: ParkingService, private route: ActivatedRoute, private _snackBar: MatSnackBar, public dialog: MatDialog){
  }

  ngOnInit() {
    this.buttonStatusList = new Array(true, true, true, true, true);
    this.parkingDetail = new ParkingDetail;
    this.parkingID = this.route.snapshot.paramMap.get('id');
    this.getData();
    }

    getData(): void {
      this.parkingService.getParking(this.parkingID).subscribe(parking => this.parkingDTO = parking, err => {
        this.openErrorSnackBar(this.checkStatusCode(err));
      }
      );
    }

    revert(number: number){
       this.buttonStatusList[number] = this.buttonStatusList[number]  == true ? false : true;
    }

    openSnackBar(parkingAttribute: string) {
      this._snackBar.open("Saved: ", parkingAttribute,{
        verticalPosition: 'top',
        duration: 4000,
      });
    }

    checkStatusCode(code: number): string {
      if (code === 128) {
        return 'Such parking doesn\'t exist!';
      }
    }

    openErrorSnackBar(message: string) {
    this._snackBar.open(message, 'Close', {
      verticalPosition: 'top',
      duration: 4000,
    });
  }

    openDialog(): void {
      const dialogRef = this.dialog.open(AddressDialog, {
        width: '250px',
      });

    dialogRef.afterClosed().subscribe(result => {
      this.parkingDetail.city = result.city;
      this.parkingDetail.street = result.street;
      this.parkingDetail.building = result.building;
    });
  }

  //  nulify(): void {
  //   let setAll = (val: any) => Object.keys(this.parkingDTO).forEach(k => this.parkingDTO[k] = val);
  //   let setNull = () => setAll(null);
  //   setNull;

    updateParking(): void {
      console.log(this.parkingDetail);

      this.parkingService.updateParking(this.parkingDetail, this.parkingID).pipe(
        flatMap(() => this.parkingService.getParking(this.parkingID))
      ).subscribe(parking => this.parkingDTO = parking, err => {
        this.openErrorSnackBar(this.checkStatusCode(err));
      }
      );
    }

  getErrorMessage() {
   if (this.input.hasError('required'))
   return 'You must enter a value';
  }
}


