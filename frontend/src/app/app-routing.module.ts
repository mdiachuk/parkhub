import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {SingupComponent} from "./singup/singup.component";
import {AppComponent} from "./app.component";
import {AddParkingComponent} from "./add-parking/add-parking.component";
import {ParkingListComponentManager} from "./parking-list-manager/parking-list.component";
import {ParkingDetailComponent} from "./parking-detail/parking-detail.component";
import {ManagerSignupComponent} from "./manager-signup/manager-signup.component";
import {AdminComponent} from "./admin/admin.component";
// import {UserPageComponent} from "./user/user.component";
import {LoginComponent} from "./login/login.component";
import {ParkoffComponent} from "./parkoff/parkoff.component";
import {ParkingDetailSlotsComponent} from "./parkings-ad/parking-detail/parking-detail.component";
import {SlotsComponent} from "./slots/slots.component";
import {BookingDetailComponent} from "./bookings/booking-detail/booking-detail.component";
import { PageComponent } from './homePage/homePage.component';
import {UserComponent} from "./user/userPage.component";
import { AdminGuard } from './guards/admin.guard';
import { ManagerGuard } from './guards/manager.guard';
import { UserGuard } from './guards/user.guard';


const routes: Routes = [
  {path: 'parkoff', component: ParkoffComponent },
  {path: 'signup/user', component: SingupComponent},
  {path: 'home', component: PageComponent},
  {path: 'signup/manager', component: ManagerSignupComponent},
  {path: 'cabinet', component: AppComponent },
  {path: 'cabinet/addParking', component: AddParkingComponent},
  {path: 'manager/cabinet', component: ParkingListComponentManager, canActivate: [ManagerGuard]},
  {path: 'manager/cabinet/:id', component: ParkingDetailComponent, canActivate: [ManagerGuard]},
  {path: 'admin', component: AdminComponent, canActivate: [AdminGuard]},

  // { path: 'parkings', component: ParkingsComponentSlots },
  { path: 'parkings/:id', component: ParkingDetailSlotsComponent },
  // { path: 'slots', component: SlotsComponent },
  { path: 'booking', component: BookingDetailComponent},

  // { path: 'user', component: UserPageComponent },
  { path: 'userPage', component: UserComponent, canActivate: [UserGuard] },
  { path: 'login', component: LoginComponent }
  // {path: '', redirectTo: 'home', pathMatch: 'full'},

];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
  declarations: []
})
export class AppRoutingModule { }





