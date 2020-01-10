import { Component, OnInit, ViewChild } from '@angular/core';

import { Observable, empty } from 'rxjs';
import {DataSource} from '@angular/cdk/collections';
import { Parking } from '../models/parking.model';
import { ParkingService1 } from '../services/parking.service';



@Component({
  selector: 'app-parking-list',
  templateUrl: './parking-list.component.html',
  styleUrls: ['./parking-list.component.scss'],


})

export class ParkingListComponent implements OnInit {

  parkings: Parking[];
  dataSource = new ParkingDataSource(this.parkingService);
  displayedColumns = ['parkingName','address', 'tariff', 'fullness'];

  constructor(private parkingService: ParkingService1) {


   }

  ngOnInit() {}

}

export class ParkingDataSource extends DataSource<any> {

  constructor(private parkingService: ParkingService1) {
    super();
  }

  connect(): Observable<Parking[]> {

  return this.parkingService.getparking();

}



  disconnect() {

  }

 }
