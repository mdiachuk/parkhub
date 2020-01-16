import { Component, OnInit } from '@angular/core';
import { AdminTicketDetail } from '../Classes/admin-ticket-detail';
import { AdminTicketService } from '../admin-ticket.service';
import { AdminService } from '../service/http-client.service'
import { Admin } from '../Classes/admin'
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-admin-ticket-detail-viewer',
  templateUrl: './admin-ticket-detail-viewer.component.html',
  styleUrls: ['./admin-ticket-detail-viewer.component.scss']
})
export class AdminTicketDetailViewerComponent implements OnInit {
  id: number;
  ticket: AdminTicketDetail;
  admin = new Admin();

  constructor(private route: ActivatedRoute,
    private router: Router, 
    private adminTicketService: AdminTicketService,
    private adminService: AdminService) { }

  ngOnInit() {
    this.ticket = new AdminTicketDetail;
    this.id = this.route.snapshot.params['id'];
    this.adminTicketService.getSingleTicket(this.id).subscribe(response =>this.ticket = response);
    
  }
  backToAdminPage(){
    this.router.navigate(['admin']);
  }
  getUserFromTicket(){
    this.adminService.getUserById(this.ticket.targetManagerId).subscribe(response =>this.admin = response);
  }
  updateUserRole(){
    this.adminService.updateRole(this.admin);
  }
  

}
