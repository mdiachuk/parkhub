import { Component, OnInit } from '@angular/core';
import { AdminTicketDetail } from '../Classes/admin-ticket-detail';
import { AdminTicketService } from '../admin-ticket.service';
import { AdminService } from '../service/http-client.service'
import { Admin } from '../Classes/admin'
import { Router, ActivatedRoute } from '@angular/router';
import { tap, flatMap } from 'rxjs/operators';
import { MatSnackBar} from '@angular/material/snack-bar';

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
    private adminService: AdminService,
    private snackBar: MatSnackBar) { }

  ngOnInit() {
    this.id = this.route.snapshot.params['id'];
    this.ticket = new AdminTicketDetail;
    this.adminTicketService.getSingleTicket(this.id).pipe(
      tap(ticket => this.ticket = ticket),
      flatMap(ticket => this.adminService.getUserById(ticket.targetManagerId))
    ).subscribe(response =>this.admin = response);  
  }

  backToAdminPage(){
    this.router.navigate(['admin']);
  }

  updateUserRole(){
    this.adminService.updateRole(this.admin);
    this.adminService.setTicketAsSolved(this.ticket);
    this.revealSnackBar();
  }

  revealSnackBar(){
    this.snackBar.open('User applied as manager, Ticket marked as solved.', 'Close', {
      duration: 4000,
      verticalPosition: 'top',
    });
  }
}