import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators, FormControl } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { PhoneNumber } from '../phoneNumber';
import { Oauth2googleService } from '../service/oauth2google.service';

@Component({
  selector: 'app-add-phone-number',
  templateUrl: './add-phone-number.component.html',
  styleUrls: ['./add-phone-number.component.scss']
})
export class AddPhoneNumberComponent implements OnInit {
  formGroup: FormGroup;
  phoneNumber : PhoneNumber;
  phoneregex: RegExp = /^380\d{9}$/


  constructor(private route: ActivatedRoute, private formBuilder: FormBuilder,private oauth2Service:Oauth2googleService) { 
    this.phoneNumber=new PhoneNumber();
  }

  ngOnInit() {

  console.log(  this.route.snapshot.queryParamMap.get('email'));
  this.phoneNumber.email= this.route.snapshot.queryParamMap.get('email');
    this.createForm();
  }

 // email: new FormControl({value: this.email, disabled: true}),

  createForm() {
    this.formGroup = this.formBuilder.group({
      'email': [this.phoneNumber.email, [Validators.required]],
     // 'email': new FormControl({value: this.phoneNumber.email, disabled: true}),
      'phoneNumber': [null,[Validators.required, Validators.pattern(this.phoneregex)]]
    });
  }


  getErrorPhoneNumber() {
    return this.formGroup.get('phoneNumber').hasError('required') ? 'Field is required' :
    this.formGroup.get('phoneNumber').hasError('pattern') ? 'Not a valid parking name': '';
  }

  onSubmit(formGroup) {
    console.log(formGroup);
     this.oauth2Service.save(formGroup)
     .subscribe( data => {
       //this.openSnackBar(("Parking created successfully."));
       console.log(data);
    },
    err => {
      //this.openSnackBar1((err.error));
      console.log(err.error);
    });
  }
}
