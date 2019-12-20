import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { BodyComponent } from './body/body.component';

const appRoutes: Routes = [
  {
    path: 'parkings',
    component: BodyComponent,
  },
  // {
  //   path: 'parkings/:id',
  //   component: BodyComponent,
  // },
  { 
    path: '', redirectTo: '/parkings', pathMatch: 'full' 
  }
];

@NgModule({
  imports: [RouterModule.forRoot(appRoutes)],
  exports: [RouterModule]
})

export class AppRoutingModule { }
