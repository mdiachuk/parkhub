import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SingupComponent } from './singup/singup.component';
import { AppComponent } from './app.component';
import { AddParkingComponent } from "./add-parking/add-parking.component";
import { ParkingListComponent } from "./parking-list/parking-list.component";
import { ParkingDetailComponent } from "./parking-detail/parking-detail.component";
import { ManagerSignupComponent } from "./manager-signup/manager-signup.component";
import { AdminComponent } from "./admin/admin.component";
// import {UserPageComponent} from "./user/user.component";
import { LoginComponent } from './login/login.component';
import { ParkoffComponent } from './parkoff/parkoff.component';
import { ParkingDetailSlotsComponent } from './parkings-ad/parking-detail/parking-detail.component';
import { SlotsComponent } from './slots/slots.component';
import { BookingDetailComponent } from './bookings/booking-detail/booking-detail.component';
import { PageComponent } from './homePage/homePage.component';
import { UserComponent } from './user/userPage.component';
import { AdminGuard } from './guards/admin.guard';
import { ManagerGuard } from './guards/manager.guard';
import { UserGuard } from './guards/user.guard';
import { AdminTicketDetailViewerComponent } from './admin-ticket-detail-viewer/admin-ticket-detail-viewer.component'

import { ResetPasswordComponent } from './reset-password/reset-password.component';
import { ForgotPasswordComponent } from './forgot-password/forgot-password.component';
import { VerifyEmailComponent } from './verify-email/verify-email.component';
import { ManagerParkingListComponent } from './manager-parking-list/manager-parking-list.component';
import { AddPhoneNumberComponent } from './add-phone-number/add-phone-number.component';

const routes: Routes = [
  { path: 'cancel', component: ParkoffComponent },
  { path: 'signup/user', component: SingupComponent },
  { path: 'home', component: PageComponent },
  { path: 'signup/manager', component: ManagerSignupComponent },
  { path: 'cabinet', component: AppComponent },
  { path: 'manager/parking', component: AddParkingComponent },
  { path: 'manager/cabinet', component: ManagerParkingListComponent, canActivate: [ManagerGuard] },
  { path: 'manager/cabinet/:id', component: ParkingDetailComponent, canActivate: [ManagerGuard] },
  { path: 'admin', component: AdminComponent, canActivate: [AdminGuard] },
  { path: 'admin/:id', component: AdminTicketDetailViewerComponent, canActivate: [AdminGuard] },

  // { path: 'parkings', component: ParkingsComponentSlots },
  { path: 'parkings/:id', component: ParkingDetailSlotsComponent },
  // { path: 'slots', component: SlotsComponent },
  { path: 'booking', component: BookingDetailComponent },

  // { path: 'user', component: UserPageComponent },
  { path: 'user', component: UserComponent, canActivate: [UserGuard] },
  { path: 'login', component: LoginComponent },
  // {path: '', redirectTo: 'home', pathMatch: 'full'},

  { path: 'forgot-password', component: ForgotPasswordComponent },
  { path: 'reset-password', component: ResetPasswordComponent },
  { path: 'verify-email', component: VerifyEmailComponent },
  { path: 'phone-number', component: AddPhoneNumberComponent }
];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
  declarations: []
})
export class AppRoutingModule { }



