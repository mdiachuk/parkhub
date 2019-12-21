import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {MatTableDataSource} from '@angular/material/table';
import { ParkingItem } from './parking-item';

@Injectable({
  providedIn: 'root'
})
export class ParkingItemServiceService {

  dataSource: MatTableDataSource<ParkingItem>;
  private parkingsUrl: string;

  constructor(private http: HttpClient) {
    this.parkingsUrl = '/api/parkings';
  }

  getAllParkings(): Observable<ParkingItem[]> {
    return this.http.get<ParkingItem[]>(this.parkingsUrl);
  }
}
