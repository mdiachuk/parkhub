import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators, FormControl } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Oauth2googleService } from '../service/oauth2google.service';
import { PhoneNumberEmail } from './PhoneNumberEmail';
import { MatSnackBar } from '@angular/material/snack-bar';
import { UserService } from '../service/http-client.service';
import { DataService } from '../DataService/data.service';
import { MatDialogRef, MatDialog } from '@angular/material';
import { AddParkingDialogComponent } from '../add-parking-dialog/add-parking-dialog.component';

@Component({
  selector: 'app-add-phone-number',
  templateUrl: './add-phone-number.component.html',
  styleUrls: ['./add-phone-number.component.scss']
})
export class AddPhoneNumberComponent implements OnInit {
  formGroup: FormGroup;
  phoneNumber : PhoneNumberEmail;
  phoneregex: RegExp = /^380\d{9}$/
  role: string;


  constructor(private route: ActivatedRoute, private formBuilder: FormBuilder,private oauth2Service:Oauth2googleService,private router: Router,private snackBar: MatSnackBar,private addservice:UserService,public data: DataService,public dialog: MatDialog) {
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
  
  openSnackBar1(message: string) {
    console.log(message);
    this.snackBar.open(message, 'Close', {
      horizontalPosition: 'center',
      verticalPosition: 'bottom',
    });
  }

  openDialog( message: string): MatDialogRef<AddParkingDialogComponent> {
    return this.dialog.open(AddParkingDialogComponent, {
      width: '350px',
      data: {message}
    });
  }


  onSubmit(formGroup) {
    console.log(formGroup);
    this.oauth2Service.save(formGroup)
      .subscribe( data => {
        localStorage.setItem('TOKEN', data.token);
        this.role = this.addservice.getUserROLE();
        // console.log(this.cookieValue)
         if (this.role === 'USER') {
           this.changeIsLogged(true);
           this.changeIsUser(true);
         }
          this.router.navigate(['/home']);
          console.log(data);
        },
        err => {
          this.openDialog((err.error)).afterClosed().subscribe(() => {
          });
          console.log(err.error);
        });
  }

  public changeIsLogged(isLogged: boolean) {
    this.data.changeIsLogged(isLogged);
  }

  public changeIsUser(isUser: boolean) {
    this.data.changeIsUser(isUser);
  }
}
