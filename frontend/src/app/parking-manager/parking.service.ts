import { Injectable } from '@angular/core';
import { ParkingItem } from './parking-item';
import { Observable, of } from 'rxjs';
import { HttpClient, HttpParams} from "@angular/common/http";
import { ParkingDetail} from './parking-detail';

@Injectable({
  providedIn: 'root'
})
export class ParkingService {

  private parkingsUrl: string;
  private parkingUrl: string;
 
  constructor(private http: HttpClient) {
    this.parkingsUrl = '/api/manager/cabinet';
  }

  getAllParkings(): Observable<ParkingItem[]> {
    return this.http.get<ParkingItem[]>(this.parkingsUrl);
  }

  getParking(id: string) : Observable<ParkingDetail>{
    // let parameters = new HttpParams().set("parkingId", id.toString());
    return this.http.get<ParkingDetail>(`${this.parkingsUrl}/${id}`);
  }
}
