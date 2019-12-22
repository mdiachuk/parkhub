import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {SingupComponent} from "./singup/singup.component";
import {PageComponent} from "./homePage/homePage.component";

const routes: Routes = [
  {path:'signup', component:SingupComponent},
  {path:'home', component:PageComponent},
  // {path: '', redirectTo: 'home', pathMatch: 'full'},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
