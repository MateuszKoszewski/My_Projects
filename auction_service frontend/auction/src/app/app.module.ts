import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { MainPageComponent } from './main-page/main-page.component';
import { FormsModule } from '@angular/forms';
import { LoggingInComponent } from './logging-in/logging-in.component';
import { AddAuctionComponent } from './add-auction/add-auction.component';

@NgModule({
  declarations: [
    AppComponent,
    MainPageComponent,
    LoggingInComponent,
    AddAuctionComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
