import { Component, OnInit } from '@angular/core';
import { AdminTicketDetail } from '../Classes/admin-ticket-detail';
import { AdminTicketCounter } from '../Classes/admin-ticket-counter';
import { AdminTicketService } from '../admin-ticket.service';
import {DataSource} from '@angular/cdk/collections';
import { Observable, of } from 'rxjs';
import { Router, ActivatedRoute } from '@angular/router';


@Component({
  selector: 'app-admin-ticket-list',
  templateUrl: './admin-ticket-list.component.html',
  styleUrls: ['./admin-ticket-list.component.scss']
})
export class AdminTicketListComponent implements OnInit {

  tickets: AdminTicketDetail[];
  dataSource = new AdminTicketListDataSource(this.adminTicketService);
  displayedColumns = ['id','ticketHighlight','supportTicketType','isSolved']
  ticketCounter$: Observable<AdminTicketCounter> = of({adminTicketCounter: 0});

  constructor(private adminTicketService: AdminTicketService,
    private route: ActivatedRoute,
    private router: Router) { 
  
  }

  ngOnInit() {
    this.reloadData();
    this.ticketCounter$ = this.getTicketCounter();
  }
  getTicketCounter(): Observable<AdminTicketCounter>{
    return this.adminTicketService.getAdminTicketCounter();
  }
  reloadData(){
    this.adminTicketService.getTicketsList().subscribe(response => this.tickets = response);
  }
  ticketDetails(id: number){
      this.router.navigate(['admin', id])
  }

}

export class AdminTicketListDataSource extends DataSource<any>{
  constructor(private adminTicketService: AdminTicketService){
    super();
  }
  connect(): Observable<AdminTicketDetail[]>{
    return this.adminTicketService.getTicketsList();
  }
  disconnect(){

  }
}