import { Component, OnInit, Input, OnDestroy } from '@angular/core';
import { Booking } from '../../interfaces/booking';
import { BookingService } from '../../serviceSlot/booking.service';
import { ActivatedRoute, Router } from '@angular/router';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import {MatSnackBar} from '@angular/material/snack-bar';


@Component({
  selector: 'app-booking-detail',
  templateUrl: './booking-detail.component.html',
  styleUrls: ['./booking-detail.component.scss']
})
export class BookingDetailComponent implements OnInit {

  booking: Booking;
  bookingForm: FormGroup;
  newBooking: Booking = {
    carNumber: "",
    phoneNumber: ""
  }

  constructor(
    private bookingService: BookingService,
    private route: ActivatedRoute,
    private router: Router,
    private snackBar: MatSnackBar
  ) { }

  ngOnInit() {
    this.bookingForm = new FormGroup({
      carNumber: new FormControl(this.newBooking.carNumber, Validators.required),
      phoneNumber: new FormControl(this.newBooking.phoneNumber, Validators.required)
    });
    this.newBooking.slotId = Number.parseInt(this.route.snapshot.queryParamMap.get("slot"));
  }

  saveData() {
    console.log(JSON.stringify(this.booking));
  }

  saveReactive() {
    this.bookingService.addBooking({...this.bookingForm.value, slotId: this.newBooking.slotId}).subscribe(o => {
      console.log("success!");
    });
  }
  openSnackBar(message: string) {
    this.snackBar.open(message, 'Close', {
      duration: 5000,
    });
  }
}


