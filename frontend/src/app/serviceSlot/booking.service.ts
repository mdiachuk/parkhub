import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Booking } from '../interfaces/booking'
import { Observable, of } from 'rxjs'
import { MessageService } from './message.service'
import { catchError, map, tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class BookingService {

  private bookingUrl = '/api/booking';  // URL to web api

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(
    private http: HttpClient,
    private messageService: MessageService
  ) { }

  /** GET booking by id. Will 404 if id not found */
  getBooking(id: number): Observable<Booking> {
    const url = `${this.bookingUrl}/${id}`;
    return this.http.get<Booking>(url).pipe(
      tap(_ => this.log(`fetched booking id=${id}`)),
      catchError(this.handleError<Booking>(`getBooking id=${id}`))
    );
  }

  

  /** POST: add a new booking to the server */
addBooking (booking: Booking): Observable<Booking> {
  return this.http.post<Booking>(this.bookingUrl, booking, this.httpOptions).pipe(
    tap((newBooking: Booking) => this.log(`added booking w/ phoneNumber=${newBooking.phoneNumber}`)),
    catchError(this.handleError<Booking>('addBooking'))
  );
}

private log(message: string) {
  this.messageService.add(`BookingService: ${message}`);
}

private handleError<T> (operation = 'operation', result?: T) {
  return (error: any): Observable<T> => {

    // TODO: send the error to remote logging infrastructure
    console.error(error); // log to console instead

    // TODO: better job of transforming error for user consumption
    this.log(`${operation} failed: ${error.message}`);

    // Let the app keep running by returning an empty result.
    return of(result as T);
  };
}

}
