import { Component, OnInit } from '@angular/core';
import { AdminTicketDetail } from '../Classes/admin-ticket-detail';
import { AdminTicketService } from '../admin-ticket.service';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-admin-ticket-detail-viewer',
  templateUrl: './admin-ticket-detail-viewer.component.html',
  styleUrls: ['./admin-ticket-detail-viewer.component.scss']
})
export class AdminTicketDetailViewerComponent implements OnInit {
  id: number;
  ticket: AdminTicketDetail;

  constructor(private route: ActivatedRoute,
    private router: Router, 
    private adminTicketService: AdminTicketService) { }

  ngOnInit() {
    this.ticket = new AdminTicketDetail;

    this.id = this.route.snapshot.params['id'];
    this.adminTicketService.getSingleTicket(this.id).subscribe(response =>this.ticket = response);
  }
  backToAdminPage(){
    this.router.navigate(['admin']);
  }

}
