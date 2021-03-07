import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AddAuctionComponent } from './add-auction/add-auction.component';
import { AdminPanelComponent } from './admin-panel/admin-panel.component';
import { LoggingInComponent } from './logging-in/logging-in.component';
import { UserGuardGuard } from './logging-in/user-guard.guard';
import { MainPageComponent } from './main-page/main-page.component';
import { UserRegisterComponent } from './user-register/user-register.component';

const routes: Routes = [{
  path: 'logg',
  component: LoggingInComponent
}, {
  path: 'addauction',
  component: AddAuctionComponent, canActivate: [UserGuardGuard]
}, {
  path: 'admin',
  component: AdminPanelComponent, canActivate: [UserGuardGuard]
},
{
  path: 'register',
  component: UserRegisterComponent
},
{
  path: 'mainpage',
  component: MainPageComponent
}, {
  path: '',
  redirectTo: '/mainpage',
  pathMatch: 'full'
}];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]

})
export class AppRoutingModule { }
