import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import {
  MatPaginatorModule,
  MatProgressSpinnerModule,
  MatSortModule,
  MatTableModule,
  MatSelectModule,
  MatIconModule,
  MatCheckboxModule,
  MatSnackBarModule
} from "@angular/material";
import { AppComponent } from './app.component';
import { ParkingListComponent } from './parking-list/parking-list.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatTableModule} from '@angular/material/table';
import { ParkingsComponent } from './parkings/parkings.component';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatSortModule } from '@angular/material/sort';
import { MatFormFieldModule} from '@angular/material/form-field';
import { MatButtonModule} from '@angular/material/button';
import { MatToolbarModule} from '@angular/material/toolbar';
import {MatInputModule} from '@angular/material';
import { HttpClientModule }    from '@angular/common/http';
import { ParkingDetailComponent } from './parking-detail/parking-detail.component';
import {MatDividerModule} from '@angular/material/divider';
import {MatListModule} from '@angular/material/list';
import {MatSidenavModule} from '@angular/material/sidenav';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ParkingListComponent } from './parking-list/parking-list.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatTableModule} from '@angular/material/table';
import { ParkingsComponent } from './parkings/parkings.component';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatSortModule } from '@angular/material/sort';
import { MatFormFieldModule} from '@angular/material/form-field';
import { MatButtonModule} from '@angular/material/button';
import { MatToolbarModule} from '@angular/material/toolbar';
import { MatInputModule} from '@angular/material';
import { HttpClientModule }    from '@angular/common/http';
import { ParkingDetailComponent } from './parking-detail/parking-detail.component';
import { MatDividerModule} from '@angular/material/divider';
import { MatListModule} from '@angular/material/list';
import { MatSidenavModule} from '@angular/material/sidenav';
import { FormsModule } from '@angular/forms';
import { MatIconModule} from '@angular/material/icon';
import { MatSnackBarModule} from '@angular/material/snack-bar';
import { MatExpansionModule} from '@angular/material/expansion';
import { MatDialogModule} from '@angular/material/dialog';
import { AddressDialog } from './parking-detail/parking-detail-dialog-component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations'
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import 'hammerjs'

import { MaterialModule } from './material.module';
import { AppComponent } from './app.component';
import { SingupComponent } from "./singup/singup.component";
import { AppRoutingModule } from "./app-routing.module";
import { HttpClientModule } from "@angular/common/http";
import { MatInputModule } from "@angular/material/input";
import { MatCardModule } from "@angular/material/card";

import { PageComponent } from './homePage/homePage.component';
import { ParkingService1 } from './services/parking.service';
import { ParkingListComponent } from './parking-list/parking-list.component';
import { ParkingListComponentManager } from './parking-list-manager/parking-list.component';

import { ParkingService, UserService } from './service/http-client.service';
import { AddParkingComponent } from "./add-parking/add-parking.component";
import { ParkingsComponent } from "./parkings/parkings.component";
import { ParkingDetailComponent } from "./parking-detail/parking-detail.component";
import { MatFormFieldModule } from "@angular/material/form-field";
import { MatButtonModule } from "@angular/material/button";
import { MatListModule } from "@angular/material/list";
import { MatDividerModule } from "@angular/material/divider";
import { MatToolbarModule } from "@angular/material/toolbar";
import { ManagerSignupComponent } from "./manager-signup/manager-signup.component";
import { AdminComponent } from "./admin/admin.component";
import { AdminTicketListComponent } from './admin-ticket-list/admin-ticket-list.component';


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
    ParkingListComponentManager,
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
    VerifyEmailComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
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
    BrowserAnimationsModule,
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
    BrowserAnimationsModule,
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
    MatIconModule
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
