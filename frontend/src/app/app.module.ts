import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms'
import { MaterialModule } from './material.module';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations'
import {Routes, RouterModule} from '@angular/router';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HttpClientModule } from '@angular/common/http'; 
import { AddParkingComponent } from './add-parking/add-parking.component';
import { ParkingServiceService } from './parking-service.service';

const appRoutes: Routes =[
  { path: 'home/cabinet/addParking', component: AddParkingComponent},
];

@NgModule({
  declarations: [
    AppComponent,

    AddParkingComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    RouterModule.forRoot(appRoutes),
    BrowserAnimationsModule,
    ReactiveFormsModule,
    MaterialModule,
    AppRoutingModule
  ],
  providers: [ParkingServiceService],
  bootstrap: [AppComponent]
})
export class AppModule { }
