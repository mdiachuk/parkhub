import { Injectable, Component } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { MessageService } from './message.service';
import { catchError, map, tap } from 'rxjs/operators';
import { Observable, of } from 'rxjs';
import { Parking } from '../interfaces/parking';
import { ParkingModel} from '../model/parking.model';
import { FormGroup, FormControl } from '@angular/forms';

@Injectable({
  providedIn: 'root'
})
export class ParkingService {

  private parkingsUrl = '/api/parkings';  // URL to web api
  
  bookingTimeForm: FormGroup;
    rangeFrom: FormControl;
    rangeTo: FormControl;

    // Min moment from
    public minFrom = new Date(2020, 1, 7, 20, 30);
    // Max moment from
    public maxFrom = new Date(2020, 1, 21, 20, 30);

    // Min moment to +1 hour!
    public minTo = new Date(2020, 1, 7, 21, 30);
    // Max moment to + 1 hour!
    public maxTo = new Date(2020, 1, 21, 20, 30);

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(
    private http: HttpClient,
    private messageService: MessageService) { }

  /** Log a HeroService message with the MessageService */
  private log(message: string) {
    this.messageService.add(`ParkingService: ${message}`);
  }

  getParkings(): Observable<Parking[]> {
    return this.http.get<Parking[]>(this.parkingsUrl)
       .pipe(
         tap(_ => this.log('fetched parkings')),
         catchError(this.handleError<Parking[]>('getParkings', []))
       );
  }

  /** GET parking by id. Will 404 if id not found */
  getParking(id: number, rangeFrom?: Date, rangeTo?: Date): Observable<Parking> {
     const url = `${this.parkingsUrl}/${id}`;
     let options = {};
     if (rangeFrom && rangeTo) {
       options = {
        params: {
          rangeFrom: `${rangeFrom.getTime()}`,
          rangeTo: `${rangeTo.getTime()}`
        }
      }
     }
     return this.http.get<Parking>(url, options).pipe(
       tap(_ => this.log(`fetched parking id=${id}`)),
       catchError(this.handleError<Parking>(`getParking id=${id}`))
   );
}

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

  //   // Let the app keep running by returning an empty result.
     return of(result as T);
   };
   }
  }

  
  @Injectable()
  export class ParkingService1 {
  
    private serviceUrl =  'api/home';
  
    constructor(private http: HttpClient) { }
  
    getparking(search?: string): Observable<ParkingModel[]> {
  
      let options = {};
      if (search && (search !== "")) {
        options = { params: {
            address: search
          } };
      }
      return this.http.get<ParkingModel[]>(this.serviceUrl, options);
  
  
    }
  
  }
  
  
