import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SingupComponent } from "./singup/singup.component";
import { AppComponent } from "./app.component";
import { AddParkingComponent } from "./add-parking/add-parking.component";
import { ParkingListComponentManager } from "./parking-list-manager/parking-list.component";
import { ParkingDetailComponent } from "./parking-detail/parking-detail.component";
import { ManagerSignupComponent } from "./manager-signup/manager-signup.component";
import { AdminComponent } from "./admin/admin.component";
import { LoginComponent } from "./login/login.component";
import { ParkoffComponent } from "./parkoff/parkoff.component";
import { ParkingDetailSlotsComponent } from "./parkings-ad/parking-detail/parking-detail.component";
import { SlotsComponent } from "./slots/slots.component";
import { BookingDetailComponent } from "./bookings/booking-detail/booking-detail.component";
import { PageComponent } from './homePage/homePage.component';
import { ResetPasswordComponent } from './reset-password/reset-password.component';
import { ForgotPasswordComponent } from './forgot-password/forgot-password.component';
import { VerifyEmailComponent } from './verify-email/verify-email.component';

const routes: Routes = [
  { path: 'parkoff', component: ParkoffComponent },
  { path: 'signup/user', component: SingupComponent },
  { path: 'home', component: PageComponent },
  { path: 'signup/manager', component: ManagerSignupComponent },
  { path: 'cabinet', component: AppComponent },
  { path: 'cabinet/addParking', component: AddParkingComponent },
  { path: 'manager/cabinet', component: ParkingListComponentManager, },
  { path: 'manager/cabinet/:id', component: ParkingDetailComponent, },
  { path: 'admin', component: AdminComponent },
  { path: 'parkings/:id', component: ParkingDetailSlotsComponent },
  { path: 'booking', component: BookingDetailComponent },
  { path: 'login', component: LoginComponent },
  { path: 'forgot-password', component: ForgotPasswordComponent },
  { path: 'reset-password', component: ResetPasswordComponent },
  { path: 'verify-email', component: VerifyEmailComponent }
];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
  declarations: []
})
export class AppRoutingModule { }



