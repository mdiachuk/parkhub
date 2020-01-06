import { Injectable } from '@angular/core';
import { ParkingItem } from './parkings/parking-item';
import { Observable, of } from 'rxjs';
import { HttpClient, HttpParams, HttpHeaders} from "@angular/common/http";
import { ParkingDetail} from './parking-detail/parking-detail';
import { catchError} from 'rxjs/operators/catchError';
import { tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class ParkingService {

  private parkingsUrl: string; 

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };
  
  constructor(private http: HttpClient) {
    this.parkingsUrl = '/api/manager/cabinet';
  }

  getAllParkings(): Observable<ParkingItem[]> {
    return this.http.get<ParkingItem[]>(this.parkingsUrl).pipe(
      catchError(this.handleError<ParkingItem[]>('getAllParkings', []))
    );
  }

  getParking(id: string) : Observable<ParkingDetail>{
    // let parameters = new HttpParams().set("parkingId", id.toString());
    return this.http.get<ParkingDetail>(`${this.parkingsUrl}/${id}`).pipe(catchError(this.handleError<ParkingDetail>('getParking')));
  }

  updateParking (parking: ParkingDetail, id: string): Observable<any> {
    return this.http.put(`${this.parkingsUrl}/${id}`, parking);
    // , parking, this.httpOptions).pipe(
    //   catchError(this.handleError<any>('updateParking'))
  }

  private handleError<T> (operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.error(error); 
      console.log(`${operation} failed: ${error.message}`);
      return of(result as T);
    };
  }
}
