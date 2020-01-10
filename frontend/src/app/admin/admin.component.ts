import { Component, OnInit } from '@angular/core';
import { AdminService} from "../service/http-client.service";
import { Admin } from '../Classes/admin'
import { FormControl } from '@angular/forms'


@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.scss']
})
export class AdminComponent implements OnInit {
  admin = new Admin();
  idGetter = new FormControl('');
  idGetterMethod(){
    this.idGetter.setValue(this.idGetter.value)
    this.adminService.getUserById(this.idGetter.value).subscribe(response => this.admin = response);
  }
  updateRoleName(){
      this.adminService.updateRole(this.admin);
      this.adminService.getUserById(this.admin.id).subscribe(response => this.admin = response)
  }


  constructor(
    private adminService: AdminService
  ) { }

  ngOnInit() {

  }

}
