import { Component, OnInit, ViewChild } from '@angular/core';
import { AdminTicketDetail } from '../Classes/admin-ticket-detail';
import { AdminTicketCounter } from '../Classes/admin-ticket-counter';
import { AdminTicketService } from '../admin-ticket.service';
import { Observable, of } from 'rxjs';
import { Router } from '@angular/router';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';

@Component({
  selector: 'app-admin-ticket-list',
  templateUrl: './admin-ticket-list.component.html',
  styleUrls: ['./admin-ticket-list.component.scss']
})

export class AdminTicketListComponent implements OnInit {

  message: String;
  dataSource = new MatTableDataSource<AdminTicketDetail>();
  displayedColumns = ['id','ticketHighlight','supportTicketType','actions'];
  ticketCounter$: Observable<AdminTicketCounter> = of({adminTicketCounter: 0});
  
  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;

  constructor(private adminTicketService: AdminTicketService,
    private router: Router) { 
  }

  ngOnInit() {
    this.reloadData();
    this.dataSource.paginator = this.paginator;
    this.ticketCounter$ = this.getTicketCounter();
  }

  getTicketCounter(): Observable<AdminTicketCounter>{
    return this.adminTicketService.getAdminTicketCounter();
  }

  reloadData(): void{
    this.adminTicketService.getTicketsList().subscribe(response => {
      this.dataSource.data = response;
    });
  }
  
  ticketDetails(id: number){
      this.router.navigate(['admin', id])
  }
}