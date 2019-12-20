import { Component, OnInit, ViewChild } from '@angular/core';
import { ParkingService } from '../services/parking.service';
import { Observable, empty } from 'rxjs';
import {DataSource} from '@angular/cdk/collections';
import { Parking } from '../models/parking.model';
import { equal } from 'assert';





@Component({
  selector: 'app-parking-list',
  templateUrl: './parking-list.component.html',
  styleUrls: ['./parking-list.component.scss'],


})



export class ParkingListComponent implements OnInit {

  parkings: Parking[];
  dataSource = new ParkingDataSource(this.parkingService);
  displayedColumns = ['parkingName','address', 'tariff', 'fullness'];

  constructor(private parkingService: ParkingService) {


   }

  ngOnInit() {



  }



}
export class ParkingDataSource extends DataSource<any> {

  constructor(private parkingService: ParkingService) {
    super();
  }

  connect(): Observable<Parking[]> {

  return this.parkingService.getparking();

}
  disconnect() {

  }

 }
