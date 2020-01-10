import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {
  MatPaginatorModule,
  MatProgressSpinnerModule,
  MatSortModule,
  MatTableModule,
  MatSelectModule,
  MatIconModule,
  MatCheckboxModule,
  MatSnackBarModule,
  MatChipsModule,
  MatFormFieldModule,
  MatInputModule,
  MatButtonModule,
  MatToolbarModule,
  MatDividerModule,
  MatListModule,
  MatSidenavModule
} from "@angular/material";

import { FormsModule } from '@angular/forms';

import { MatExpansionModule} from '@angular/material/expansion';
import { MatDialogModule} from '@angular/material/dialog';
import { AddressDialog } from './parking-detail/parking-detail-dialog-component';

import {  ReactiveFormsModule } from '@angular/forms';
import 'hammerjs'

import { MaterialModule } from './material.module';

import { SingupComponent } from "./singup/singup.component";



import { MatCardModule } from "@angular/material/card";

import { PageComponent } from './homePage/homePage.component';
import { ParkingService1 } from './services/parking.service';


import { ParkingService, UserService } from './service/http-client.service';
import { AddParkingComponent } from "./add-parking/add-parking.component";
import { ManagerSignupComponent } from "./manager-signup/manager-signup.component";
import { AdminComponent } from "./admin/admin.component";
import { AdminTicketListComponent } from './admin-ticket-list/admin-ticket-list.component';
import { SlotService } from './serviceSlot/slot.service';
import { DataService } from './DataService/data.service';
import { ParkhubInterceptorComponent } from './parkhub-interceptor/parkhub-interceptor.component';
import { AlertDialogComponent } from './alert-dialog/alert-dialog.component';
import { VerifyEmailComponent } from './verify-email/verify-email.component';
import { AdminTicketDetailViewerComponent } from './admin-ticket-detail-viewer/admin-ticket-detail-viewer.component';
import { LoginComponent } from './login/login.component';
import { ParkoffComponent } from './parkoff/parkoff.component';
import { SlotsComponent } from './slots/slots.component';
import { ParkingDetailSlotsComponent } from './parkings-ad/parking-detail/parking-detail.component';
import { ParkingsComponentSlots } from './parkings-ad/parkings.component';
import { BookingDetailComponent } from './bookings/booking-detail/booking-detail.component';
import { BookingsComponent } from './bookings/bookings.component';
import { UserComponent } from './user/userPage.component';
import { ResetPasswordComponent } from './reset-password/reset-password.component';
import { ForgotPasswordComponent } from './forgot-password/forgot-password.component';
import { AppComponent } from './app.component';
import { ParkingListComponent } from './parking-list/parking-list.component';
import { ParkingsComponent } from './parkings/parkings.component';
import { ParkingDetailComponent } from './parking-detail/parking-detail.component';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { ManagerParkingListComponent } from './manager-parking-list/manager-parking-list.component';
import { from } from 'rxjs';


@NgModule({
  declarations: [
    AppComponent,
    ParkingListComponent,
    ParkingsComponent,
    ParkingDetailComponent,
    AppComponent,
    ParkingListComponent,
    ParkingsComponent,
    ParkingDetailComponent,
    AddressDialog,
    AppComponent,
    SingupComponent,
    PageComponent,
    ParkingListComponent,
    AddParkingComponent,
    ParkingsComponent,
    ParkingDetailComponent,
    ManagerSignupComponent,
    AdminComponent,
    LoginComponent,
    ParkoffComponent,
    AlertDialogComponent,
    SlotsComponent,
    ParkingDetailSlotsComponent,
    ParkingsComponentSlots,
    BookingDetailComponent,
    BookingsComponent,
    AdminTicketListComponent,
    AdminTicketDetailViewerComponent,
    UserComponent,
    BookingsComponent,
    ResetPasswordComponent,
    ForgotPasswordComponent,
    VerifyEmailComponent,
    ManagerParkingListComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    MatTableModule,
    MatPaginatorModule,
    MatSortModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    HttpClientModule,
    MatToolbarModule,
    MatDividerModule,
    MatListModule,
    MatSidenavModule,
    BrowserModule,
    AppRoutingModule,
    MatTableModule,
    MatPaginatorModule,
    MatSortModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    HttpClientModule,
    MatToolbarModule,
    MatDividerModule,
    MatListModule,
    MatSidenavModule,
    FormsModule,
    MatIconModule,
    MatSnackBarModule,
    MatExpansionModule,
    MatDialogModule,
    BrowserModule,
    FormsModule,
    MaterialModule,
    AppRoutingModule,
    HttpClientModule,
    MatInputModule,
    MatCardModule,
    MatTableModule,
    MatPaginatorModule,
    MatSortModule,
    MatProgressSpinnerModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatButtonModule,
    MatDividerModule,
    MatListModule,
    MatSelectModule,
    MatIconModule,
    MatToolbarModule,
    MatCheckboxModule,
    MatSnackBarModule,
    MatDialogModule,
    MatChipsModule,
    MatSidenavModule,
    FormsModule,
    MatIconModule,
    BrowserAnimationsModule
  ],
  providers: [ParkingService, SlotService, ParkingService1, DataService, UserService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: ParkhubInterceptorComponent,
      multi: true
    }],
  bootstrap: [AppComponent],
  entryComponents: [AlertDialogComponent, AddressDialog]
})
export class AppModule { }
