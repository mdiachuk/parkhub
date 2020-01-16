import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {FormGroup, FormControl, Validators, AbstractControl} from '@angular/forms';

@Component({
    selector: 'app-date-time-picker',
    templateUrl: './date-time-picker.component.html',
    styleUrls: ['./date-time-picker.component.scss']
})
export class DateTimePickerComponent implements OnInit {

    bookingTimeForm: FormGroup;
    rangeFrom: FormControl;
    rangeTo: FormControl;

    // Min moment from
    public minFrom = new Date(2020, 0, 7, 20, 30);
    // Max moment from
    public maxFrom = new Date(2020, 0, 21, 20, 30);

    // Min moment to +1 hour!
    public minTo = new Date(2020, 0, 7, 21, 30);
    // Max moment to + 1 hour!
    public maxTo = new Date(2020, 0, 21, 20, 30);

    constructor() {
    }

    ngOnInit() {
        this.rangeFrom = new FormControl('', [Validators.required]);
        //, Validators.min(this.rangeFrom)
        this.rangeTo = new FormControl('', [Validators.required]);
        this.bookingTimeForm = new FormGroup({
            rangeFrom: this.rangeFrom,
            rangeTo: this.rangeTo
        });
    }

    isErrorRangeFrom() {
        return this.bookingTimeForm.get('rangeFrom').hasError('required') ? 'Field is required' : '';
    }

    isErrorRangeTo() {
        return this.bookingTimeForm.get('rangeTo').hasError('required') ? 'Field is required' :
            this.bookingTimeForm.get('rangeTo').hasError('<rangeFrom') ? 'Must be greater than range from' : '';
    }

    saveReactive() {
        console.log("success!");
    }
}
