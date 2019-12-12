import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {SingupComponent} from "./singup/singup.component";

const routes: Routes = [
  {path:'singup_c', component:SingupComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
