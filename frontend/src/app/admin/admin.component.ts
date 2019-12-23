import { Component, OnInit } from '@angular/core';
import { AdminService} from "../service/http-client.service";
import { Admin } from '../Classes/admin'
import { ActivatedRoute, Router} from '@angular/router'
import { HttpClient } from '@angular/common/http'
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
  roleControl = new FormControl('');
  updateRoleName(){
      this.roleControl.setValue(this.roleControl.value)
      this.admin.userRole = this.roleControl.value;
      this.adminService.updateRole(this.admin);
  }


  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private adminService: AdminService,
    private httpClient: HttpClient,
  ) { }

  ngOnInit() {

  }

}
