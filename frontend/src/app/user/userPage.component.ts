import { AbstractControl } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { Observable, empty } from 'rxjs';
import { FormControl, Validators, FormGroup, FormBuilder } from '@angular/forms';
import {UserService} from '../service/http-client.service';
import {UserInfo} from '../interfaces/userInfo';
import { MatSnackBar } from '@angular/material/snack-bar';

var i = 0;
var j = 0;


@Component({
  selector: 'app-userPage',
  templateUrl: './userPage.component.html',
  styleUrls: ['./userPage.component.css']

})


export class UserComponent implements OnInit {

  user: UserInfo;
  updateForm: FormGroup;

  // tslint:disable-next-line:no-shadowed-variable
  constructor( private uf: FormBuilder, private UserService: UserService, private _snackBar: MatSnackBar) { }

  ngOnInit() {

    this.UserService.getData().subscribe((data: UserInfo) => {
      this.user = data;
      this.updateForm = this.uf.group({
        firstName: [data.firstName],
        lastName: [data.lastName],
        email: [data.email],
        phoneNumber: [data.phoneNumber],
        userPassword: [''],
        newPassword: [''],
        newConfirmPassword: [''],

      }, { validator: UserChangeConfirm.findPassword });
    });


  }
  EditUserInfo() {
    if (i == 0) {
      document.getElementById("displayUserInfo").style.display = "none";
      document.getElementById("editUserInfo").style.display = "";
      document.getElementById("editButton").innerText = "Close";
      i++;
    }
    else {
      document.getElementById("displayUserInfo").style.display = "";
      document.getElementById("editUserInfo").style.display = "none";
      document.getElementById("editButton").innerText = "Edit";
      i--;
    }
  }
  PostData() {
    this.UserService.PostData({...this.updateForm.value,id: this.UserService.getUserID()}).subscribe(res => {
      console.log(res);
      alert("Changes were accepted");
    });
  }
  changePassword() {

    if (j == 0) {
      document.getElementById("ChangePassword").style.display = "";
      document.getElementById("buttonChangePassword").innerText = "Close";
      document.getElementById("buttonSavePassword").style.display = "";
      j++;
    } else {
      document.getElementById("ChangePassword").style.display = "none";
      document.getElementById("buttonSavePassword").style.display = "none";
      document.getElementById("buttonChangePassword").innerText = "Change Password";
      j--;
    }

  }

  PostPassword() {
    this.UserService.PostDataPassword({
      id: this.UserService.getUserID(),
      password: this.updateForm.get('userPassword').value,
      newPassword: this.updateForm.get('newPassword').value
    }).subscribe(res => {
        this._snackBar.open("Password Changed!", "Ok", {
          duration: 2000,
        });
      },
      err => {
        this._snackBar.open("Failed to change password!", "Ok", {
          duration: 2000,
        });
      });
  }


}
export class UserChangeConfirm {
  static findPassword(control: AbstractControl) {
    let Newpassword = control.get('newPassword').value;
    let NewConfirmPassword = control.get('newConfirmPassword').value;
    if (Newpassword !== NewConfirmPassword) {
      control.get('newConfirmPassword').setErrors({ NewConfirmPassword: true });
    }
    else {
      return null;
    }
  }
}

