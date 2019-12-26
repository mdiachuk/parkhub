import { Component, OnInit } from '@angular/core';
import { ParkingService} from "../parking.service";
import { ParkingDetail} from './parking-detail';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';




@Component({
  selector: 'app-parking-detail',
  templateUrl: './parking-detail.component.html',
  styleUrls: ['./parking-detail.component.scss']
})
export class ParkingDetailComponent implements OnInit {

  parkingID: string;
  parkingDetail: ParkingDetail;
  resourceParkingDTO: ParkingDetail;
  buttonStatusList: Array<boolean>;
  

  constructor(private parkingService: ParkingService, private route: ActivatedRoute){
  }


  getData(): void {
    this.parkingService.getParking(this.parkingID).subscribe(parking => this.parkingDetail = parking);
  }

  ngOnInit() {
    this.buttonStatusList = new Array(true, true, true, true, true, true);
    this.resourceParkingDTO = new ParkingDetail;
    this.parkingID = this.route.snapshot.paramMap.get('id');
    this.getData();
    }

    revert(number: number){
       this.buttonStatusList[number] = this.buttonStatusList[number]  == true ? false : true;
    }
}
