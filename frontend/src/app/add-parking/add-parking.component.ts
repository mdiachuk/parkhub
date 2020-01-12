import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {MatSnackBar} from '@angular/material/snack-bar';
import {ParkingServiceService} from '../parking-service.service';
import {TranslateArrayService} from '../service/translatearray.service';
import {TranslateService} from '@ngx-translate/core';
import { Observable } from 'rxjs/Observable';
import { Oauth2googleService } from '../service/oauth2google.service';
import { UserService } from '../service/http-client.service';
import { NewParking } from './NewParking';


@Component({
  selector: 'app-add-parking',
  templateUrl: './add-parking.component.html',
  styleUrls: ['./add-parking.component.scss']
})
export class AddParkingComponent implements OnInit {
  parking: NewParking = new NewParking()
  formGroup: FormGroup;
  CityArray: string[] = [];
  nameregex: RegExp = /^[a-zA-Z 0-9-]+$/;
  onlyPositiveIntegersregex: RegExp = /^[1-9]+[0-9]*$/;
  streetregex: RegExp = /^([a-zA-Z -]+)$/;
  buildingregex: RegExp = /^([a-zA-Z0-9 -]+)$/;
  parkNameValidator: any;
  MatSnackBar: any;

  constructor(private formBuilder: FormBuilder, private route: ActivatedRoute,
              private router: Router, private snackBar: MatSnackBar,
              private parkingService: ParkingServiceService,
              private translateArrayService: TranslateArrayService,
              private translateService: TranslateService,
              private service:Oauth2googleService,private addservice:UserService) {
  }

  ngOnInit() {
    this.createForm();
    this.translateArrayService.asObservable().subscribe(tr => {
      this.CityArray = tr;
    });
  }

  createForm() {
    this.formGroup = this.formBuilder.group({
      parkingName: [null, [Validators.required, Validators.pattern(this.nameregex)]],
      slotsNumber: [null, [Validators.required, Validators.pattern(this.onlyPositiveIntegersregex)]],
      tariff: [null, [Validators.required, Validators.pattern(this.onlyPositiveIntegersregex)]],
      city: [null, [Validators.required]],
      street: [null, [Validators.required, Validators.pattern(this.streetregex)]],
      building: [null, [Validators.required, Validators.pattern(this.buildingregex)]]
    });
  }


  getErrorCity() {
    return this.formGroup.get('city').hasError('required') ? this.translateService.instant('FieldIsRequired') : '';
  }

  getErrorParkingName() {
    return this.formGroup.get('parkingName').hasError('required') ? this.translateService.instant('FieldIsRequired') :
      this.formGroup.get('parkingName').hasError('pattern') ? this.translateService.instant('NotAValidParkingName') : '';
  }

  getErrorSlotsNumber() {
    return this.formGroup.get('slotsNumber').hasError('required') ? this.translateService.instant('FieldIsRequired') :
      this.formGroup.get('slotsNumber').hasError('pattern') ? this.translateService.instant('NotAValidNumberOfSlotsShouldBePositiveInteger') : '';

  }

  getErrorTariff() {
    return this.formGroup.get('tariff').hasError('required') ? this.translateService.instant('FieldIsRequired') :
      this.formGroup.get('tariff').hasError('pattern') ? this.translateService.instant('NotAValidNumberOfSlotsShouldBePositiveInteger') : '';

  }

  getErrorStreet() {
    return this.formGroup.get('street').hasError('required') ? this.translateService.instant('FieldIsRequired') :
      this.formGroup.get('street').hasError('pattern') ? this.translateService.instant('NotAValidNameOfStreet') : '';

  }
  getErrorBuilding() {
    return this.formGroup.get('building').hasError('required') ? this.translateService.instant('FieldIsRequired') :
      this.formGroup.get('building').hasError('pattern') ? this.translateService.instant('NotAValidBuilding') : '';
  }

  openSnackBar(message: string) {
    this.snackBar.open(message, 'Close', {
      horizontalPosition: 'center',
      verticalPosition: 'top',
      panelClass: ['blue-snackbar']
    });
  }

  openSnackBar1(message: string) {
    console.log(message);
    this.snackBar.open(message, 'Close', {
      horizontalPosition: 'center',
      verticalPosition: 'top',
    });
  }

  onSubmit(formGroup) {
    console.log(formGroup);
    for(let property in formGroup){this.parking[property] = formGroup[property];}
    this.parking["id"] = Number(this.addservice.getUserID());
    console.log(this.parking)
    this.parkingService.save(this.parking)
      .subscribe( data => {
          this.openSnackBar(('Parking created successfully.'));
        },
        err => {
          this.openSnackBar1((err.error));
        });
  }
}
