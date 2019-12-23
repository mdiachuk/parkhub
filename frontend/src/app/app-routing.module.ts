import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {SingupComponent} from "./singup/singup.component";
import {AppComponent} from "./app.component";
import {AddParkingComponent} from "./add-parking/add-parking.component";
import {ParkingListComponentManager} from "./parking-list-manager/parking-list.component";
import {ParkingDetailComponent} from "./parking-detail/parking-detail.component";
import {ManagerSignupComponent} from "./manager-signup/manager-signup.component";
import {AdminComponent} from "./admin/admin.component";
// import {UserComponent} from "./user/user.component";
import {LoginComponent} from "./login/login.component";
import {ParkoffComponent} from "./parkoff/parkoff.component";
import { PageComponent } from './homePage/homePage.component';


const routes: Routes = [
  {path: 'parkoff', component: ParkoffComponent },
  {path: 'signup/user', component:SingupComponent},
  {path: 'home', component:PageComponent},
  {path: 'signup/manager', component:ManagerSignupComponent},
  {path: 'cabinet', component: AppComponent },
  {path: 'cabinet/addParking', component: AddParkingComponent},
  {path: 'manager/cabinet', component: ParkingListComponentManager,},
  {path: 'manager/cabinet/:id', component: ParkingDetailComponent,},
  {path: 'admin', component: AdminComponent},

  // { path: 'user', component: UserComponent },
  { path: 'login', component: LoginComponent },
  // {path: '', redirectTo: 'home', pathMatch: 'full'},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
