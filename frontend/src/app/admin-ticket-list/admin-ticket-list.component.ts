import { Component, OnInit } from '@angular/core';
import { AdminTicketDetail } from '../Classes/admin-ticket-detail';
import { AdminTicketCounter } from '../Classes/admin-ticket-counter';
import { AdminTicketService } from '../admin-ticket.service';
import {DataSource} from '@angular/cdk/collections';
import { Observable } from 'rxjs';


@Component({
  selector: 'app-admin-ticket-list',
  templateUrl: './admin-ticket-list.component.html',
  styleUrls: ['./admin-ticket-list.component.scss']
})
export class AdminTicketListComponent implements OnInit {

  tickets: AdminTicketDetail[];
  dataSource = new AdminTicketListDataSource(this.adminTicketService);
  displayedColumns = ['id','description','isSolved','supportTicketType']
  ticketCounter: AdminTicketCounter;

  constructor(private adminTicketService: AdminTicketService) { 
  
  }

  ngOnInit() {
    this.reloadData();
    this.getTicketCounter();
  }
  getTicketCounter(){
    this.adminTicketService.getAdminTicketCounter().subscribe(response =>this.ticketCounter = response)
  }
  reloadData(){
    this.adminTicketService.getTicketsList().subscribe(response => this.tickets = response);
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