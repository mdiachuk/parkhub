import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Slot } from '../interfaces/slot'
import { Observable, of } from 'rxjs'
import { MessageService } from './message.service'
import { catchError, map, tap } from 'rxjs/operators';


@Injectable({
  providedIn: 'root'
})
export class SlotService {

  constructor(
    private http: HttpClient,
    private messageService: MessageService) { }

    /** GET slots from the server */
  getSlots(): Observable<Slot[]> {
    return this.http.get<Slot[]>(this.slotsUrl)
    .pipe(
      tap(_ => this.log('fetched slots')),
      catchError(this.handleError<Slot[]>('getSlots', []))
    );
  }

  /** GET slot by id. Will 404 if id not found */
getSlot(id: number): Observable<Slot> {
  const url = `${this.slotsUrl}/${id}`;
  return this.http.get<Slot>(url).pipe(
    tap(_ => this.log(`fetched slot id=${id}`)),
    catchError(this.handleError<Slot>(`getSlot id=${id}`))
  );
}

  /** Log a SlotService message with the MessageService */
private log(message: string) {
  this.messageService.add(`SlotService: ${message}`);
}

private slotsUrl = 'api/slots';  // URL to web api

/**
 * Handle Http operation that failed.
 * Let the app continue.
 * @param operation - name of the operation that failed
 * @param result - optional value to return as the observable result
 */
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
