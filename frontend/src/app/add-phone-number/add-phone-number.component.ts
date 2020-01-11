import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators, FormControl } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Oauth2googleService } from '../service/oauth2google.service';
import { PhoneNumberEmail } from './PhoneNumberEmail';

@Component({
  selector: 'app-add-phone-number',
  templateUrl: './add-phone-number.component.html',
  styleUrls: ['./add-phone-number.component.scss']
})
export class AddPhoneNumberComponent implements OnInit {
  formGroup: FormGroup;
  phoneNumber : PhoneNumberEmail;
  phoneregex: RegExp = /^380\d{9}$/


  constructor(private route: ActivatedRoute, private formBuilder: FormBuilder,private oauth2Service:Oauth2googleService,private router: Router,) { 
    this.phoneNumber=new PhoneNumberEmail();
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
    this.formGroup.get('phoneNumber').hasError('pattern') ? 'Phone number should start with 380 and contain 12 numbers': '';
  }

  onSubmit(formGroup) {
    console.log(formGroup);
     this.oauth2Service.save(formGroup)
     .subscribe( data => {
      this.router.navigate(['/home']);
       console.log(data);
    },
    err => {
      //this.openSnackBar1((err.error));
      console.log(err.error);
    });
  }
}
