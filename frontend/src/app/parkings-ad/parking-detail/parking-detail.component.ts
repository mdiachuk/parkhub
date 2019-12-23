import { Component, OnInit, Input, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';
import { Parking } from '../../interfaces/parking';
import { ParkingService } from '../../serviceSlot/parking.service';

@Component({
  selector: 'app-parking-detail',
  templateUrl: './parking-detail.component.html',
  styleUrls: ['./parking-detail.component.scss']
})
export class ParkingDetailSlotsComponent implements OnInit, OnDestroy {

  @Input()parking: Parking;

  constructor(
  private route: ActivatedRoute,
  private parkingService: ParkingService,
  private location: Location
  ) { }

  ngOnInit(): void {
    this.getParking();
  }

  getParking(): void {
    const id = this.route.snapshot.paramMap.get('id');
    this.parkingService.getParking(Number.parseInt(id))
      .subscribe(parking => this.parking = parking);
  }

  ngOnDestroy(): void {
  }
}
