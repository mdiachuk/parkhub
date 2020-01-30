import { Component, OnInit, Input, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';
import { Parking } from '../../interfaces/parking';
import { ParkingService } from '../../service/parking.service'
import { FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-parking-detail',
  templateUrl: './parking-detail.component.html',
  styleUrls: ['./parking-detail.component.scss']
})
export class ParkingDetailSlotsComponent implements OnInit, OnDestroy {

  bookingTimeForm: FormGroup;
  rangeFrom: FormControl;
  rangeTo: FormControl;

  // Min moment from
  public minFrom = new Date(Date.now());
  // Max moment from
  public maxFrom = new Date(2020, 1, 14, 16, 15);
  // Min moment to +1 hour!
  public minTo = new Date(2020, 0, 31, 17, 15);
  // Max moment to + 1 hour!
  public maxTo = new Date(2020, 1, 14, 16, 15);

  @Input()parking: Parking;

  constructor(
    private route: ActivatedRoute,
    private parkingService: ParkingService,
    private location: Location
  ) { }

  ngOnInit(): void {
    this.rangeFrom = new FormControl('', [Validators.required]);
    //, Validators.min(this.rangeFrom)
    this.rangeTo = new FormControl('', [Validators.required]);
    this.bookingTimeForm = new FormGroup({
      rangeFrom: this.rangeFrom,
      rangeTo: this.rangeTo
    });
  }

  getParking(rangeFrom: Date, rangeTo: Date): void {
    const id = this.route.snapshot.paramMap.get('id');
    this.parkingService.getParking(Number.parseInt(id), rangeFrom, rangeTo)
      .subscribe(parking => this.parking = parking);
  }

  ngOnDestroy(): void {
  }

  isErrorRangeFrom() {
    return this.bookingTimeForm.get('rangeFrom').hasError('required') ? 'Field is required' : '';
  }

  isErrorRangeTo() {
    return this.bookingTimeForm.get('rangeTo').hasError('required') ? 'Field is required' :
      this.bookingTimeForm.get('rangeTo').hasError('<rangeFrom') ? 'Must be greater than range from': '';
  }

  saveReactive() {
    this.getParking(this.rangeFrom.value, this.rangeTo.value);
  }
}
