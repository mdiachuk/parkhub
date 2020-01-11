import { Component, OnInit, ViewChild, Input, OnChanges, SimpleChanges, SimpleChange } from '@angular/core';

import { Observable, empty } from 'rxjs';
import {DataSource} from '@angular/cdk/collections';
import { Parking } from '../models/parking.model';
import { ParkingService1 } from '../services/parking.service';



@Component({
  selector: 'app-parking-list',
  templateUrl: './parking-list.component.html',
  styleUrls: ['./parking-list.component.scss'],


})

export class ParkingListComponent implements OnInit, OnChanges {

  private _search: string;



  @Input()
  set search(val: string) {
    console.log(val)
    this._search = val;
  }

  get search(): string {
    return this._search;
  }

  parkings: Parking[];
  dataSource = new ParkingDataSource(this.parkingService);
  displayedColumns = ['parkingName','address', 'tariff', 'fullness'];

  constructor(private parkingService: ParkingService1) {


  }

  ngOnInit() {}


  ngOnChanges(changes: SimpleChanges) {
    const currentItem: SimpleChange = changes.item;
    if (typeof currentItem === 'string')
      this.dataSource = new ParkingDataSource(this.parkingService, currentItem);
  }
}

export class ParkingDataSource extends DataSource<any> {

  constructor(private parkingService: ParkingService1, private search?: string) {
    super();
  }

  connect(): Observable<Parking[]> {
    console.log(this.search)
    return this.parkingService.getparking(this.search);

  }



  disconnect() {

  }

}
