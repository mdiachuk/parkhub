import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AddParkingComponent } from './add-parking/add-parking.component';
import { AppComponent } from './app.component';


const routes: Routes = [
  { path: 'home/cabinet', component: AppComponent },
  { path: 'home/cabinet/addParking', component: AddParkingComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
