import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { AdminTicketDetail } from './Classes/admin-ticket-detail'
import { AdminTicketCounter } from './Classes/admin-ticket-counter'

@Injectable({
  providedIn: 'root'
})
export class AdminTicketService {

  constructor(private http:HttpClient) { }

  getTicketsList():Observable<AdminTicketDetail[]>{
    return this.http.get<AdminTicketDetail[]>(`/api/admin/ticketlist`);
  }

  getAdminTicketCounter(): Observable<AdminTicketCounter>{
    return this.http.get<AdminTicketCounter>('/api/admin/ticketlistcounter')
  }
}
