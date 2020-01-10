import { Component, OnInit } from '@angular/core';
import { Parking } from '../interfaces/parking';
import { ParkingService} from '../serviceSlot/parking.service';

@Component({
  selector: 'app-parkings-slots',
  templateUrl: './parkings.component.html',
  styleUrls: ['./parkings.component.scss']
})
export class ParkingsComponentSlots implements OnInit {
  parkings: Parking[];

  constructor(private parkingService: ParkingService) { }

  ngOnInit() {
    this.getParkings();
  }

  getParkings(): void {
    this.parkingService.getParkings()
    .subscribe(parkings => this.parkings = parkings);
  }

}
