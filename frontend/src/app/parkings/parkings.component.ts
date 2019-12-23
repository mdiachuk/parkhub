import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import {MatTableDataSource} from '@angular/material/table';
import {MatSort} from '@angular/material/sort';
import { ParkingService} from "../parking-manager/parking.service";
import { ParkingItem } from '../parking-manager/parking-item';



@Component({
  selector: 'app-parkings',
  templateUrl: './parkings.component.html',
  styleUrls: ['./parkings.component.scss']
})
export class ParkingsComponent implements OnInit {

  constructor(private parkingService: ParkingService){
  }

  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;
  @ViewChild(MatSort, {static: true}) sort: MatSort;

  displayedColumns = ['name', 'address'];
  dataSource = new MatTableDataSource<ParkingItem>();

  getData(): void {
    this.parkingService.getAllParkings()
      .subscribe(data => {
        this.dataSource.data = data;
      });
  }

  ngOnInit() {
    this.getData();
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  }

  applyFilter(filterValue: string) {
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

}
