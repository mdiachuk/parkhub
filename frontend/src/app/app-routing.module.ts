import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ParkingListComponent } from './parking-list/parking-list.component';
import {ParkingDetailComponent} from './parking-detail/parking-detail.component'
import { AddParkingComponent } from './add-parking/add-parking.component';
import { AppComponent } from './app.component';

const appRoutes: Routes = [
  {path: 'home/cabinet', component: ParkingListComponent},
  {path: 'parkings/:id', component: ParkingDetailComponent},
  {path: 'home/cabinet/addParking', component: AddParkingComponent}
];



@NgModule({
  imports: [RouterModule.forRoot(appRoutes)],
  exports: [RouterModule]
})

export class AppRoutingModule { }
