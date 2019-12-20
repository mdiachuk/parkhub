import { Component } from '@angular/core';
import { FormBuilder, FormGroup, FormControl, Validators, AbstractControl } from '@angular/forms';
import { Observable } from 'rxjs/Observable';
import { ParkingServiceService } from '../parking-service.service';
import { Parking } from '../parking';
import { ActivatedRoute, Router } from '@angular/router';




@Component({
  selector: 'app-add-parking',
  templateUrl: './add-parking.component.html',
  styleUrls: ['./add-parking.component.scss']
})
export class AddParkingComponent {
  
  formGroup: FormGroup;
  parking: Parking;
  titleAlert: string = 'This field is required';
  post: any = '';
  CityArray: any = ['Kyiv', 'Lviv', 'Chernivtsi', 'Dnipro', 'Kharkiv'];
  nameregex: RegExp = /^[a-zA-Z]*$/
  onlyPositiveIntegersregex: RegExp = /^[1-9]+[0-9]*$/  
  streetregex: RegExp = /^([a-zA-Z -]+)$/
  buildingregex: RegExp = /^([a-zA-Z0-9 -]+)$/
  parkNameValidator: any;
  //parkingService: ParkingServiceService;
  
  constructor(private formBuilder: FormBuilder,private route: ActivatedRoute, 
    private router: Router,
       private parkingService: ParkingServiceService) { 
        this.parking = new Parking();
        //this.parkingService = new ParkingServiceService();
       }

  ngOnInit() {
    this.createForm();
  }

  createForm() {
    // let nameregex: RegExp = /^[a-zA-Z\s]*$/
    // let onlyPositiveIntegersregex: RegExp = /^[1-9]+[0-9]*$/  
    // let streetregex: RegExp = /^([a-zA-Z -]+)$/
    // let buildingregex: RegExp = /^\w-$/ //not sure if correct(ask)
    this.formGroup = this.formBuilder.group({
      'parkingName': [null, [Validators.required, Validators.pattern(this.nameregex)]],
      'slotsNumber': [null, [Validators.required, Validators.pattern(this.onlyPositiveIntegersregex)]],
      'tariff': [null, [Validators.required, Validators.pattern(this.onlyPositiveIntegersregex)]],
      'city': [null],
      'street': [null,[Validators.required, Validators.pattern(this.streetregex)]],
      'building': [null,[Validators.required, Validators.pattern(this.buildingregex)]]
     // 'description': [null, [Validators.required, Validators.minLength(5), Validators.maxLength(10)]],
     // 'validate': ''
    });
  }


  // checkPassword(control) {
  //   let enteredPassword = control.value
  //   let passwordCheck = /^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.{8,})/;
  //   return (!passwordCheck.test(enteredPassword) && enteredPassword) ? { 'requirements': true } : null;
  // }

  // checkInUseParkingName(control) {
  //   // mimic http database access
  //   let db = ['tony@gmail.com'];
  //   return new Observable(observer => {
  //     setTimeout(() => {
  //       let result = (db.indexOf(control.value) !== -1) ? { 'alreadyInUse': true } : null;
  //       observer.next(result);
  //       observer.complete();
  //     }, 4000)
  //   })
  // }




  getErrorParkingName() {
    return this.formGroup.get('parkingName').hasError('required') ? 'Field is required' :
      this.formGroup.get('parkingName').hasError('pattern') ? 'Not a valid parking name': '';
       // this.formGroup.get('parkingName').hasError('alreadyInUse') ? 'This emailaddress is already in use' : '';
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



  onSubmit(formGroup) {
    console.log(this.parkingService,formGroup);
     this.parkingService.save(formGroup)
     .subscribe( data => {
       alert("User created successfully.");
    },
    err => {
      alert(err.error);
    });//if request == ok -> redirect to home/cabinet/manager

    
}


}