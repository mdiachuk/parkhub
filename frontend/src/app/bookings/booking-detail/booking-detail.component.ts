import {Component, OnInit, Input, OnDestroy} from '@angular/core';
import {Booking} from '../../interfaces/booking';
import {BookingService} from '../../service/booking.service';
import {ActivatedRoute, Router} from '@angular/router';
import {FormGroup, FormControl, Validators, AbstractControl} from '@angular/forms';
import {MatSnackBar} from '@angular/material/snack-bar';
import {MatDialog} from '@angular/material/dialog';
import {MonerisComponent} from '../../moneris/moneris.component';

@Component({
  selector: 'app-booking-detail',
  templateUrl: './booking-detail.component.html',
  styleUrls: ['./booking-detail.component.scss']
})
export class BookingDetailComponent implements OnInit {

  booking: Booking;
  bookingForm: FormGroup;
  carNumber: FormControl;
  phoneNumber: FormControl;
  newBooking: Booking = {
    carNumber: "",
    phoneNumber: ""
  }
  carRegex: RegExp = /[A-Z]{2}\d{4}[A-Z]{2}/;
  phoneRegex: RegExp = /^380\d{9}$/;

  constructor(
    private bookingService: BookingService,
    private route: ActivatedRoute,
    private router: Router,
    private snackBar: MatSnackBar,
    private dialog: MatDialog,
  ) {
  }

  ngOnInit() {
    this.carNumber = new FormControl('', [Validators.required, Validators.pattern(this.carRegex)]);
    this.phoneNumber = new FormControl('', [Validators.required, Validators.pattern(this.phoneRegex)]);
    this.bookingForm = new FormGroup({
      carNumber: this.carNumber,
      phoneNumber: this.phoneNumber
    });
    this.newBooking.slotId = Number.parseInt(this.route.snapshot.queryParamMap.get("slot"));
    this.newBooking.rangeFrom = Number.parseInt(this.route.snapshot.queryParamMap.get("rangeFrom"));
    this.newBooking.rangeTo = Number.parseInt(this.route.snapshot.queryParamMap.get("rangeTo"));
    this.newBooking.tariff = Number.parseInt(this.route.snapshot.queryParamMap.get("tariff"));
  }

  isErrorCarNumber() {
    return this.bookingForm.get('carNumber').hasError('required') ? 'Field is required' :
      this.bookingForm.get('carNumber').hasError('pattern') ? 'Not a valid car number' : '';
  }

  isErrorPhoneNumber() {
    return this.bookingForm.get('phoneNumber').hasError('required') ? 'Field is required' :
      this.bookingForm.get('phoneNumber').hasError('pattern') ? 'Not a valid phone number' : '';
  }

  saveData() {
    console.log(JSON.stringify(this.booking));
  }

  saveReactive() {
    this.bookingService.addBooking({
      ...this.bookingForm.value,
      slotId: this.newBooking.slotId,
      rangeFrom: this.newBooking.rangeFrom,
      rangeTo: this.newBooking.rangeTo,
      tariff: this.newBooking.tariff
    }).subscribe(o => {
      this.dialog.open(MonerisComponent, {
        data: {
          payment: o
        }
      });
    });
  }

  openSnackBar(message: string) {
    this.snackBar.open(message, 'Close', {
      horizontalPosition: 'center',
      verticalPosition: 'top',
      duration: 20000,
    });
    let snackBarRef = this.snackBar.open(message, 'Proceed payment', {
      duration: 20000,
    });
    snackBarRef.onAction().subscribe(a => {
      this.router.navigate(['home']);
    })
  }
}


