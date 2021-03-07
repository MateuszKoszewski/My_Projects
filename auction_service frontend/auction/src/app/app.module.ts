import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FormsModule } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { TokenInterceptorService } from './logging-in/token-interceptor.service';
import { MainPageModule } from './main-page/main-page.module';
import { LoggingInModule } from './logging-in/logging-in.module';
import { AddAuctionModule } from './add-auction/add-auction.module';
import { AdminPanelModule } from './admin-panel/admin-panel.module';
import { UserRegisterComponent } from './user-register/user-register.component';
import { UserRegisterModule } from './user-register/user-register.module';






@NgModule({
  declarations: [
    AppComponent

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    MainPageModule,
    LoggingInModule,
    AddAuctionModule,
    UserRegisterModule,
    AdminPanelModule
  ],
  providers: [{
    provide: HTTP_INTERCEPTORS,
    useClass: TokenInterceptorService,
    multi: true
  }],
  bootstrap: [AppComponent]
})
export class AppModule { }
