import { Component, OnInit } from '@angular/core';
import { AdminService } from '../service/admin.service'
import { Admin } from '../Classes/admin'
import { ActivatedRoute, Router} from '@angular/router'
import { HttpClient } from '@angular/common/http'


@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.scss']
})
export class AdminComponent implements OnInit {
  admin = new Admin()
  

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private adminService: AdminService,
    private httpClient: HttpClient,
  ) { }

  ngOnInit() {
    this.try();
    //this.adminService.getUserById(1).subscribe(response => this.admin = response);
  }
  try(){
    this.httpClient.get<Admin>("http://localhost:8080/admin/1").subscribe(response => this.admin = response);
  }

}
