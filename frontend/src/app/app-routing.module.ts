import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {SingupComponent} from "./singup/singup.component";
import {PageComponent} from "./homePage/homePage.component";
import {AppComponent} from "./app.component";
import {AddParkingComponent} from "./add-parking/add-parking.component";

const routes: Routes = [
  {path:'signup', component:SingupComponent},
  {path:'home', component:PageComponent},
  { path: 'home/cabinet', component: AppComponent },
  { path: 'home/cabinet/addParking', component: AddParkingComponent}
  // {path: '', redirectTo: 'home', pathMatch: 'full'},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
