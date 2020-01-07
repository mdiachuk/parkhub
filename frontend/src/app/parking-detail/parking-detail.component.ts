import { Component, OnInit } from '@angular/core';
import { ParkingService} from "../parking.service";
import { ParkingDetail} from './parking-detail';
import { ActivatedRoute } from '@angular/router';
import { MatSnackBar} from '@angular/material/snack-bar';
import { MatDialog} from '@angular/material/dialog';
import { AddressDialog } from './parking-detail-dialog-component';



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
  

  constructor(private parkingService: ParkingService, private route: ActivatedRoute, private _snackBar: MatSnackBar, public dialog: MatDialog){
  }


  getData(): void {
    this.parkingService.getParking(this.parkingID).subscribe(parking => this.parkingDTO = parking);
  }

  ngOnInit() {
    this.buttonStatusList = new Array(true, true, true, true);
    this.parkingDetail = new ParkingDetail;
    this.parkingID = this.route.snapshot.paramMap.get('id');
    this.getData();
    }

    revert(number: number){
       this.buttonStatusList[number] = this.buttonStatusList[number]  == true ? false : true;
    }

    openSnackBar(parkingAttribute: string) {
      this._snackBar.open("Saved: ", parkingAttribute,{
        duration: 2000,
      });
    }

    openDialog(): void {
      const dialogRef = this.dialog.open(AddressDialog, {
        width: '250px',
      });

    dialogRef.afterClosed().subscribe(result => {
      this.parkingDTO.city = result.city;
      this.parkingDTO.street = result.street;
      this.parkingDTO.building = result.building;
    });
  }

   nulify(): void {
    let setAll = (val: any) => Object.keys(this.parkingDTO).forEach(k => this.parkingDTO[k] = val);
    let setNull = () => setAll(null);
    setNull;

  //   function setAll(val: any) {
  //     Object.keys(this.parkingDTO).forEach(function(index) {
  //       this.parkingDTO[index] = val;
  //     });
  // }
  // function setNull() {
  //     setAll(null);
  // }
  };


    saveToDTO(): void {
      Object.assign(this.parkingDTO, this.parkingDetail);
      console.log(this.parkingDTO);
      this.parkingService.updateParking(this.parkingDTO, this.parkingID).subscribe(r=>{});
    }
}


 