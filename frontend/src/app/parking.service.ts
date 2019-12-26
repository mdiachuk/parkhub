import { Injectable } from '@angular/core';
import { ParkingItem } from './parkings/parking-item';
import { Observable, of } from 'rxjs';
import { HttpClient, HttpParams} from "@angular/common/http";
import { ParkingDetail} from './parking-detail/parking-detail';

@Injectable({
  providedIn: 'root'
})
export class ParkingService {

  private parkingsUrl: string; 
  constructor(private http: HttpClient) {
    this.parkingsUrl = '/api/parkings';
  }

  getAllParkings(): Observable<ParkingItem[]> {
    return this.http.get<ParkingItem[]>(this.parkingsUrl);
  }

  getParking(id: string) : Observable<ParkingDetail>{
    // let parameters = new HttpParams().set("parkingId", id.toString());
    return this.http.get<ParkingDetail>(`${this.parkingsUrl}/${id}`);
  }
}
