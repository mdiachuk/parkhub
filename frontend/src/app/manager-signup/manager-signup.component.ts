import { Component, OnInit } from '@angular/core';
import { FormControl, Validators, FormGroup, FormBuilder, AbstractControl } from '@angular/forms';
import { ConfirmPasswordValidator } from "../validation/confirm-password.validator";
import { Manager } from '../model/manager';
import { SignUpService } from "../service/signup.service";
import { HttpClient } from '@angular/common/http';
import { AddParkingDialogComponent } from '../add-parking-dialog/add-parking-dialog.component';
import { MatDialogRef, MatDialog } from '@angular/material';

@Component({
  selector: 'app-manager-signup',
  templateUrl: './manager-signup.component.html',
  styleUrls: ['./manager-signup.component.scss']
})
export class ManagerSignupComponent implements OnInit {

  nameGroup: FormGroup;
  companyGroup: FormGroup;
  contactsGroup: FormGroup;
  passwordGroup: FormGroup;
  commentGroup: FormGroup;
  manager: Manager;
  message: string;
  isCreated: boolean;
  loading: boolean;

  constructor(private signUpService: SignUpService, private fb: FormBuilder,
    private http: HttpClient, private dialog: MatDialog) { }

  ngOnInit() {
    this.nameGroup = this.fb.group({
      firstName: [''],
      lastName: ['']
    });
    this.companyGroup = this.fb.group({
      companyName: [''],
      usreouCode: ['']
    });
    this.contactsGroup = this.fb.group({
      email: [''],
      phoneNumber: ['']
    });
    this.passwordGroup = this.fb.group({
      password: [''],
      confirmPassword: ['']
    }, { validator: ConfirmPasswordValidator.matchPassword });
    this.commentGroup = this.fb.group({
      comment: [''],
      checkbox: false
    });
    this.isCreated = false;
    this.loading = false;
  }

  register(): void {
    this.loading = true;
    this.manager = new Manager(this.nameGroup.get('firstName').value,
      this.nameGroup.get('lastName').value,
      this.companyGroup.get('companyName').value,
      this.companyGroup.get('usreouCode').value,
      this.contactsGroup.get('email').value,
      this.contactsGroup.get('phoneNumber').value,
      this.passwordGroup.get('password').value,
      this.passwordGroup.get('confirmPassword').value,
      this.commentGroup.get('comment').value);
    this.signUpService.registerManager(this.manager).subscribe(response => {
      this.isCreated = true;
      this.loading = false;
    }, err => {
      this.message = err.error;
      this.openDialog(this.message);
      this.loading = false;
    });
  }

  openDialog( message: string): MatDialogRef<AddParkingDialogComponent> {
    return this.dialog.open(AddParkingDialogComponent, {
      width: '350px',
      data: {message}
    });
  }
}
