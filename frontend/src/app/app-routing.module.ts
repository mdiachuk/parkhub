import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {ParkoffComponent} from './parkoff/parkoff.component';

const routes: Routes = [
  { path: 'parkoff', component: ParkoffComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
