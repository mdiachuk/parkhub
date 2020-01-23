import { Component, OnInit } from '@angular/core';
import { AdminService} from "../service/http-client.service";

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.scss']
})
export class AdminComponent implements OnInit {

  constructor(
    private adminService: AdminService
  ) { }

  ngOnInit() {

  }

}