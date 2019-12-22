import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ParkingListComponent } from './parking-list/parking-list.component';
import {ParkingDetailComponent} from './parking-detail/parking-detail.component'

const appRoutes: Routes = [
  {
    path: 'parkings',
    component: ParkingListComponent,
  },
  {
    path: 'parkings/:id',
    component: ParkingDetailComponent,
  },
];



@NgModule({
  imports: [RouterModule.forRoot(appRoutes)],
  exports: [RouterModule]
})

export class AppRoutingModule { }
