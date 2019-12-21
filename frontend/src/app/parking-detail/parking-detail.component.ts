import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-parking-detail',
  templateUrl: './parking-detail.component.html',
  styleUrls: ['./parking-detail.component.scss']
})
export class ParkingDetailComponent implements OnInit {

  displayedColumns = ['Name', 'Address', 'Floors', 'Places', 'Tariff', 'Coefficient'];

  constructor() { }

  ngOnInit() {
  }

}
