import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AddAuctionComponent } from './add-auction/add-auction.component';
import { AdminPanelComponent } from './admin-panel/admin-panel.component';
import { BoughtAuctionsComponent } from './bought-auctions/bought-auctions.component';
import { LoggingInComponent } from './logging-in/logging-in.component';
import { UserGuardGuard } from './logging-in/user-guard.guard';
import { DisplayAuctionComponent } from './main-page/display-auction/display-auction.component';
import { MainPageComponent } from './main-page/main-page.component';
import { MyAuctionsComponent } from './my-auctions/my-auctions.component';
import { NotificationsComponent } from './notifications/notifications.component';
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
  path: 'boughtAuctions',
  component: BoughtAuctionsComponent, canActivate: [UserGuardGuard]
},
{
  path: 'register',
  component: UserRegisterComponent
},
{
  path: 'notifications',
  component: NotificationsComponent
},
{
  path: 'displayAuction',
  component: DisplayAuctionComponent
},
{
  path: 'myAuctions',
  component: MyAuctionsComponent, canActivate: [UserGuardGuard]
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
