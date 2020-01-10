import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import 'hammerjs';

import { MaterialModule } from './material.module';
import { AppComponent } from './app.component';
import { SingupComponent } from './singup/singup.component';
import { AppRoutingModule } from './app-routing.module';
import {HttpClientModule , HttpClient} from '@angular/common/http';
import { MatInputModule } from '@angular/material/input';
import { MatCardModule } from '@angular/material/card';

import { PageComponent } from './homePage/homePage.component';
import { ParkingService1 } from './services/parking.service';
import { ParkingListComponent } from './parking-list/parking-list.component';
import { ParkingListComponentManager } from './parking-list-manager/parking-list.component';

import { ParkingService, UserService } from './service/http-client.service';
import { AddParkingComponent } from './add-parking/add-parking.component';
import { ParkingsComponent } from './parkings/parkings.component';
import { ParkingDetailComponent } from './parking-detail/parking-detail.component';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatButtonModule } from '@angular/material/button';
import { MatListModule } from '@angular/material/list';
import { MatDividerModule } from '@angular/material/divider';
import { MatToolbarModule } from '@angular/material/toolbar';
import { ManagerSignupComponent } from './manager-signup/manager-signup.component';
import { AdminComponent } from './admin/admin.component';
import { TranslateModule, TranslateLoader } from '@ngx-translate/core';
import { TranslateHttpLoader } from '@ngx-translate/http-loader';

export function HttpLoaderFactory(http: HttpClient) {
  return new TranslateHttpLoader(http);
}

import {
  MatPaginatorModule,
  MatProgressSpinnerModule,
  MatSortModule,
  MatTableModule,
  MatSelectModule,
  MatIconModule,
  MatCheckboxModule,
  MatSnackBarModule
} from '@angular/material';
import { UserComponent } from './user/userPage.component';
import { LoginComponent } from './login/login.component';
import { AlertDialogComponent } from './alert-dialog/alert-dialog.component';
import { ParkoffComponent } from './parkoff/parkoff.component';
import { MatDialogModule } from '@angular/material/dialog';
import { SlotsComponent } from './slots/slots.component';
import { BookingDetailComponent } from './bookings/booking-detail/booking-detail.component';
import { BookingsComponent } from './bookings/bookings.component';
import { MatChipsModule } from '@angular/material/chips';
import { ParkingsComponentSlots } from './parkings-ad/parkings.component';
import { ParkingDetailSlotsComponent } from './parkings-ad/parking-detail/parking-detail.component';
import { SlotService } from './serviceSlot/slot.service';
import { from } from 'rxjs';
import {TranslateArrayService} from './service/translatearray.service';
import { ForgotPasswordComponent } from './forgot-password/forgot-password.component';
import { ResetPasswordComponent } from './reset-password/reset-password.component';
import { VerifyEmailComponent } from './verify-email/verify-email.component';
import { MatSidenavModule } from '@angular/material/sidenav';


import { DataService } from './DataService/data.service';
import { ParkhubInterceptorComponent } from './parkhub-interceptor/parkhub-interceptor.component';
import { HTTP_INTERCEPTORS } from '@angular/common/http';

@NgModule({
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    FormsModule,
    MaterialModule,
    AppRoutingModule,
    HttpClientModule,
    TranslateModule.forRoot({
      loader: {
        provide: TranslateLoader,
        useFactory: HttpLoaderFactory,
        deps: [HttpClient]
      }
    }),
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
  declarations: [
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
    UserComponent,
    BookingsComponent,
    ResetPasswordComponent,
    ForgotPasswordComponent,
    VerifyEmailComponent
    // UserComponent
  ],
  providers: [ParkingService, SlotService, ParkingService1, DataService, UserService, TranslateArrayService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: ParkhubInterceptorComponent,
      multi: true
    }],
  bootstrap: [AppComponent],
  entryComponents: [AlertDialogComponent]
})
export class AppModule { }
