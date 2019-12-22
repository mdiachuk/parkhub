import { Component } from '@angular/core';
import { FormBuilder, FormGroup, FormControl, Validators, AbstractControl } from '@angular/forms';
import { Observable } from 'rxjs/Observable';
import { ParkingServiceService } from '../parking-service.service';
import { Parking } from '../parking';
import { ActivatedRoute, Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';


@Component({
  selector: 'app-add-parking',
  templateUrl: './add-parking.component.html',
  styleUrls: ['./add-parking.component.scss']
})
export class AddParkingComponent {
  
  formGroup: FormGroup;
  CityArray: any = ['Kyiv', 'Lviv', 'Chernivtsi', 'Dnipro', 'Kharkiv'];
  nameregex: RegExp = /^[a-zA-Z 0-9-]+$/
  onlyPositiveIntegersregex: RegExp = /^[1-9]+[0-9]*$/  
  streetregex: RegExp = /^([a-zA-Z -]+)$/
  buildingregex: RegExp = /^([a-zA-Z0-9 -]+)$/
  parkNameValidator: any;
  MatSnackBar: any;
  
  constructor(private formBuilder: FormBuilder,private route: ActivatedRoute, 
    private router: Router,private snackBar: MatSnackBar,
       private parkingService: ParkingServiceService) { 
       }

  ngOnInit() {
    this.createForm();
  }

  createForm() {
    this.formGroup = this.formBuilder.group({
      'parkingName': [null, [Validators.required, Validators.pattern(this.nameregex)]],
      'slotsNumber': [null, [Validators.required, Validators.pattern(this.onlyPositiveIntegersregex)]],
      'tariff': [null, [Validators.required, Validators.pattern(this.onlyPositiveIntegersregex)]],
      'city': [null, [Validators.required]],
      'street': [null,[Validators.required, Validators.pattern(this.streetregex)]],
      'building': [null,[Validators.required, Validators.pattern(this.buildingregex)]]
    });
  }


  getErrorCity() {
    return this.formGroup.get('city').hasError('required') ? 'Field is required' : '';
  }

  getErrorParkingName() {
    return this.formGroup.get('parkingName').hasError('required') ? 'Field is required' :
      this.formGroup.get('parkingName').hasError('pattern') ? 'Not a valid parking name': '';
  }

  getErrorSlotsNumber() {
    return this.formGroup.get('slotsNumber').hasError('required') ? 'Field is required' :
      this.formGroup.get('slotsNumber').hasError('pattern') ? 'Not a valid number of slots.Should be positive integer': '';
       
  }

  getErrorTariff() {
    return this.formGroup.get('tariff').hasError('required') ? 'Field is required' :
      this.formGroup.get('tariff').hasError('pattern') ? 'Not a valid tariff.Should be positive integer': '';
       
  }

  getErrorStreet() {
    return this.formGroup.get('street').hasError('required') ? 'Field is required' :
      this.formGroup.get('street').hasError('pattern') ? 'Not a valid name of street.': '';
       
  }
  getErrorBuilding() {
    return this.formGroup.get('building').hasError('required') ? 'Field is required ' :
      this.formGroup.get('building').hasError('pattern') ? 'Not a valid building' : '';
  }

  openSnackBar(message: string) {
    this.snackBar.open(message, 'Close', {
      horizontalPosition: 'center',
      verticalPosition: 'top',
      panelClass: ['blue-snackbar']
    });
  }

  openSnackBar1(message: string) {
    this.snackBar.open(message, 'Close', {
      horizontalPosition: 'center',
      verticalPosition: 'top',
    });
  }
  onSubmit(formGroup) {
    console.log(formGroup);
     this.parkingService.save(formGroup)
     .subscribe( data => {
       this.openSnackBar(("Parking created successfully."));
    },
    err => {
      this.openSnackBar1((err.error));
    });

    
}


}