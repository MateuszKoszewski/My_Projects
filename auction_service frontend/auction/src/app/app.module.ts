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
import { UserRegisterModule } from './user-register/user-register.module';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MaterialsModule } from './materials/materials.module';
import { MyAuctionsComponent } from './my-auctions/my-auctions.component';
import { MyAuctionsModule } from './my-auctions/my-auctions.module';


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
    AdminPanelModule,
    BrowserAnimationsModule,
    MaterialsModule,
    MyAuctionsModule
  ],
  providers: [{
    provide: HTTP_INTERCEPTORS,
    useClass: TokenInterceptorService,
    multi: true
  }],
  bootstrap: [AppComponent]
})
export class AppModule { }
